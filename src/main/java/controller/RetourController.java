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

public class RetourController {

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

    @RequestMapping(value = "/retour", method = RequestMethod.GET)
    public String showFormRetour(HttpServletRequest request) {
        List<AdherentEntity> adherents = adherentService.findAll(); // tu dois avoir cette méthode
        request.setAttribute("listAdherent", adherents);
        return "form-retour";
    }

@RequestMapping(value = "/retour", method = RequestMethod.POST)
public String afficherPretsEnCours(HttpServletRequest request, Model model) {
    Integer idAdherent = Integer.parseInt(request.getParameter("idAdherent"));

    List<AdherentEntity> adherents = adherentService.findAll();
    List<PretEntity> pretsEnCours = pretService.getPretsEnCoursByAdherent(idAdherent);

    request.setAttribute("listAdherent", adherents);
    request.setAttribute("pretsEnCours", pretsEnCours);
    request.setAttribute("idAdherentSelectionne", idAdherent);

    return "form-retour";
}


     @PostMapping("/confirmerRetour")
    public String confirmerRetour(HttpServletRequest request, Model model) {
        try {
            Integer idPret = Integer.parseInt(request.getParameter("idPret"));
            LocalDate dateRetour = LocalDate.parse(request.getParameter("dateRetour"));
            pretService.effectuerRetour(idPret, dateRetour);

            model.addAttribute("messageSuccess", "Retour effectué avec succès.");
        } catch (Exception e) {
            model.addAttribute("messageError", "Erreur : " + e.getMessage());
        }

        List<AdherentEntity> adherents = adherentService.findAll();
        request.setAttribute("listAdherent", adherents);
        return "form-retour";
    }

}
