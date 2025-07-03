package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.AdherentEntity;
import entities.InscriptionEntity;
import entities.TypeAccountEntity;
import entities.StatutAdherentEntity;
import entities.UsersEntity;
import entities.ProfilEntity;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import service.InscriptionService;
import service.ProfilService;
import service.TypeAccountService;
import service.UsersService;
import service.AdherentService;
import service.StatutAdherentService;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class AdherentController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private TypeAccountService typeAccountService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private ProfilService profilService;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private StatutAdherentService statutAdherentService;

    @RequestMapping(value = "/creationAdherent", method = RequestMethod.GET)
    public String showAdherentForm(Model model) {
        List<ProfilEntity> profils = profilService.findAll();
        model.addAttribute("profils", profils);
        return "form-adherent";
    }

    // @Transactional
    @RequestMapping(value = "/creationAdherent", method = RequestMethod.POST)
    public String handleCreateAdherent(HttpServletRequest request, Model model) {

        String adherentname = request.getParameter("adherentname");
        Integer idProfil = Integer.parseInt(request.getParameter("profilType")); // Corrigé pour matcher le nom dans le formulaire
        String dateNaissanceStr = request.getParameter("datenaissance");
        String dateCreationStr = request.getParameter("datecreation");

        if (adherentname == null || adherentname.trim().isEmpty()) {
            model.addAttribute("error", "Le nom de l'adhérent est obligatoire");
            return "form-adherent";
        }

        try {

            LocalDate dateNaissance = dateNaissanceStr != null ? LocalDate.parse(dateNaissanceStr) : null;
            LocalDate dateCreation = LocalDate.parse(dateCreationStr);

            InscriptionEntity inscription = new InscriptionEntity();
            inscription.setDateDebutAbonnement(dateCreation);
            inscription.setDateFinAbonnement(dateCreation.plusYears(1)); // Ajout d'un an

            // Sauvegarde de l'inscription pour obtenir son ID
            InscriptionEntity savedInscription = inscriptionService.save(inscription);

            // 2. Création de l'adhérent
            ProfilEntity profil = profilService.findById(idProfil);
            StatutAdherentEntity stat = statutAdherentService.findById(1);

            AdherentEntity adherent = new AdherentEntity();
            adherent.setAdherentName(adherentname);
            adherent.setProfil(profil);
            adherent.setDateNaissance(dateNaissance);
            adherent.setInscription(savedInscription); // Association avec l'inscription créée
            adherent.setStatut(stat);// Valeur par défaut, à adapter selon votre modèle

            adherentService.save(adherent);

            model.addAttribute("succes", "Adhérent ajouté avec succès");
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Format de date invalide");
        } catch (RuntimeException e) {
            model.addAttribute("error", "Erreur lors de la création: " + e.getMessage());
        }

        List<ProfilEntity> profils = profilService.findAll();
        model.addAttribute("profils", profils);

        return "form-adherent";
    }

}
