package controller;

import entities.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur Web MVC pour la gestion des prêts.
 */
@Controller

public class ReservationController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HistoriqueReservationService historiqueReservationService;

    @RequestMapping(value = "/createReservation", method = RequestMethod.GET)
    public String listerUtilities(Model model) {
        List<LivreEntity> tags = livreService.findAll();

        List<AdherentEntity> adherent = adherentService.findAll();
        model.addAttribute("listLivre", tags);
        model.addAttribute("listAdherent", adherent);

        return "reservation-form";
    }

    @RequestMapping(value = "/createReservation", method = RequestMethod.POST)
    public String handleCreateReservation(HttpServletRequest request, Model model) {
        Integer idAdherent = Integer.parseInt(request.getParameter("idAdherent"));
        Integer idLivre = Integer.parseInt(request.getParameter("idLivre"));
        LocalDate dateReservation = LocalDate.parse(request.getParameter("dateReservation"));
        LocalDate dateMouvement = LocalDate.parse(request.getParameter("dateMouvement"));


        AdherentEntity adherent = adherentService.findById(idAdherent);
        LivreEntity livre = livreService.findById(idLivre);


        try {
            // 🔐 Règle 1 : Adhérent actif
            if (adherent == null || adherent.getStatut().getIdStatut().equals(2)) {
                throw new RuntimeException("L’adhérent est inactif ou introuvable.");
            }

            // 🔐 Règle 2 : Âge minimum requis
            int age = java.time.Period.between(adherent.getDateNaissance(), dateReservation).getYears();
            CategorieAgeEntity catAge = livre.getCategorieAge();
            if (age < catAge.getAgeMin()) {
                throw new RuntimeException("L’adhérent est trop jeune pour emprunter ce livre.");
            }

            // 🔐 Règle 3 : Pénalité active (à adapter selon ton design exact)
            if (adherentService.aUnePenaliteActive(idAdherent, dateReservation)) {
                throw new RuntimeException("L’adhérent a une pénalité active.");
            }

            // 🔐 Règle 5 : Disponibilité de l’exemplaire
            ExemplaireEntity exemplaireDispo = exemplaireService.getExemplaireDisponible(idLivre);
            if (exemplaireDispo == null) {
                throw new RuntimeException("Aucun exemplaire disponible pour ce livre.");
            }

            // 🔁 Enregistrement du prêt
            ReservationEntity reservation = new ReservationEntity();
            reservation.setAdherent(adherent);
            reservation.setExemplaire(exemplaireDispo);
            reservation.setDateReservation(dateReservation);

            ReservationEntity reservationEnregistre = reservationService.save(reservation);

            String status = "attente";
            HistoriqueReservationEntity historique = new HistoriqueReservationEntity();
            historique.setReservation(reservationEnregistre);
            historique.setDateMouvement(dateMouvement);
            historique.setStatut(status);

            historiqueReservationService.save(historique);

            model.addAttribute("messageSuccess", "Réservation envoyé avec succès. En attente de validation");
            model.addAttribute("listLivre", livreService.findAll());
            model.addAttribute("listAdherent", adherentService.findAll());
            return "reservation-form";

        } catch (RuntimeException e) {
            model.addAttribute("messageError", "Erreur : " + e.getMessage());
            model.addAttribute("listLivre", livreService.findAll());
            model.addAttribute("listAdherent", adherentService.findAll());
            return "reservation-form";
        }

    }

     @GetMapping("/ReservationAdmin")
    public String pageReservationAdmin(HttpServletRequest req) {
        req.setAttribute("listReservation", historiqueReservationService.getDerniersReservationsEnAttenteParReservation());
        return "form-admin-reservation";
    }

    @PostMapping("/confirmerReservationAdmin")
    public String confirmerAdmin(HttpServletRequest req, Model model) {
        try {
            Integer idReservation = Integer.parseInt(req.getParameter("idReservation"));
            LocalDate dateValidation = LocalDate.parse(req.getParameter("dateValidation"));
            reservationService.confirmReservation(idReservation, dateValidation);

            model.addAttribute("messageSuccess",
                    "Reservation confirmé.");
        } catch (Exception e) {
            model.addAttribute("messageError", e.getMessage());
        }
        req.setAttribute("listReservation", historiqueReservationService.getDerniersReservationsEnAttenteParReservation());
        return "form-admin-reservation";
    }

}
