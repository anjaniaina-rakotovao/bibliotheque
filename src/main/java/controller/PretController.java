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

public class PretController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PretService pretService;

    @Autowired
    private HistoriquePretService historiquePretService;

    @RequestMapping(value = "/createPret", method = RequestMethod.GET)
    public String listerUtilities(Model model) {
        List<LivreEntity> tags = livreService.findAll();
        List<TypePretEntity> typePret = typePretService.findAll();
        List<AdherentEntity> adherent = adherentService.findAll();
        model.addAttribute("listLivre", tags);
        model.addAttribute("listTypePret", typePret);
        model.addAttribute("listAdherent", adherent);

        return "pret-form";
    }

    @RequestMapping(value = "/createPret", method = RequestMethod.POST)
    public String handleCreatePret(HttpServletRequest request, Model model) {
        Integer idAdherent = Integer.parseInt(request.getParameter("idAdherent"));
        Integer idLivre = Integer.parseInt(request.getParameter("idLivre"));
        Integer idTypePret = Integer.parseInt(request.getParameter("idTypePret"));
        LocalDate datePret = LocalDate.parse(request.getParameter("datePret"));

        AdherentEntity adherent = adherentService.findById(idAdherent);
        LivreEntity livre = livreService.findById(idLivre);
        TypePretEntity typePret = typePretService.findById(idTypePret);

        try {
            // 🔐 Règle 1 : Adhérent actif
            if (adherent == null || adherent.getStatut().getIdStatut().equals(2)) {
                throw new RuntimeException("L’adhérent est inactif ou introuvable.");
            }

            // 🔐 Règle 2 : Âge minimum requis
            int age = java.time.Period.between(adherent.getDateNaissance(), datePret).getYears();
            CategorieAgeEntity catAge = livre.getCategorieAge();
            if (age < catAge.getAgeMin()) {
                throw new RuntimeException("L’adhérent est trop jeune pour emprunter ce livre.");
            }

            // 🔐 Règle 3 : Pénalité active (à adapter selon ton design exact)
            if (adherentService.aUnePenaliteActive(idAdherent, datePret)) {
                throw new RuntimeException("L’adhérent a une pénalité active.");
            }

            // 🔐 Règle 4 : Quotas de prêt
            int quotaAutorisé = adherent.getProfil().getQuotaPret();
           long nbPretsActifs = pretService.countPretsActifs(idAdherent);

            if (nbPretsActifs >= quotaAutorisé) {
                throw new RuntimeException("L’adhérent a atteint son quota de prêts actifs.");
            }

            // 🔐 Règle 5 : Disponibilité de l’exemplaire
            ExemplaireEntity exemplaireDispo = exemplaireService.getExemplaireDisponible(idLivre);
            if (exemplaireDispo == null) {
                throw new RuntimeException("Aucun exemplaire disponible pour ce livre.");
            }

            // 🔁 Enregistrement du prêt
            PretEntity pret = new PretEntity();
            pret.setAdherent(adherent);
            pret.setExemplaire(exemplaireDispo);
            pret.setDatePret(datePret);
            pret.setTypePret(typePret);

            // Sauvegarde
            // PretEntity pretEnregistre = pretService.save(pret);
            PretEntity pretEnregistre = pretService.creerPret(adherent, exemplaireDispo, typePret, datePret);
            // 🔁 Historique statut "EnCours"
            StatutPretEntity statutEnCours = new StatutPretEntity();
            statutEnCours.setIdStatut(1); // ou récupérer depuis service
            HistoriquePretEntity historique = new HistoriquePretEntity();
            historique.setPret(pretEnregistre);
            historique.setStatut(statutEnCours);
            historique.setDateStatut(datePret);
            historiquePretService.save(historique);

            model.addAttribute("messageSuccess", "Prêt créé avec succès.");
            return "accueil";

        } catch (RuntimeException e) {
            model.addAttribute("messageError", "Erreur : " + e.getMessage());
            model.addAttribute("listLivre", livreService.findAll());
            model.addAttribute("listTypePret", typePretService.findAll());
            model.addAttribute("listAdherent", adherentService.findAll());
            return "pret-form";
        }

    }

}
