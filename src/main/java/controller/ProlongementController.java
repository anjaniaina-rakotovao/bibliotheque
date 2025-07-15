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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProlongementController {

    @Autowired
    private AdherentService adherentService;
    @Autowired
    private PretService pretService;
    @Autowired
    private ProlongementPretService prolongementPretService;

    @GetMapping("/prolongement")
    public String pageProlongement(HttpServletRequest req) {
        req.setAttribute("listAdherent", adherentService.findAll());
        return "prolongement-form";
    }

    @PostMapping("/prolongement")
    public String afficherPrets(HttpServletRequest req) {

        Integer id = Integer.parseInt(req.getParameter("idAdherent"));
        req.setAttribute("listAdherent", adherentService.findAll());
        req.setAttribute("idAdherentSelectionne", id);

        List<PretEntity> prets = pretService.getPretsEnCoursByAdherent(id);
        req.setAttribute("pretsEnCours", prets);

        // Calculer les dates de fin pour chaque prêt
        Map<Integer, LocalDate> datesFin = new HashMap<>();
        for (PretEntity p : prets) {
            LocalDate fin = pretService.calculerDateFinEffective(p);
            datesFin.put(p.getIdPret(), fin);
        }
        req.setAttribute("datesFinPret", datesFin);

        return "prolongement-form";
    }

    @PostMapping("/confirmerProlongement")
    public String confirmer(HttpServletRequest req, Model model) {
        try {
            Integer idPret = Integer.parseInt(req.getParameter("idPret"));
            Integer duree = Integer.parseInt(req.getParameter("duree"));
            LocalDate dateProlongement = LocalDate.parse(req.getParameter("dateProlongement"));
            LocalDate nouvelle = pretService.prolongerPret(idPret, duree, dateProlongement);

            model.addAttribute("messageSuccess",
                    "Prêt prolongé jusqu'au " + nouvelle + ".");
        } catch (Exception e) {
            model.addAttribute("messageError", e.getMessage());
        }
        req.setAttribute("listAdherent", adherentService.findAll());
        return "prolongement-form";
    }



    @GetMapping("/prolongementAdmin")
    public String pageProlongementAdmin(HttpServletRequest req) {
        req.setAttribute("listProlongement", prolongementPretService.getDerniersProlongementsEnAttenteParPret());
        return "form-admin-prolongement";
    }

    @PostMapping("/confirmerProlongementAdmin")
    public String confirmerAdmin(HttpServletRequest req, Model model) {
        try {
            Integer idProlongement = Integer.parseInt(req.getParameter("idProlongement"));
            LocalDate dateValidation = LocalDate.parse(req.getParameter("dateValidation"));
            prolongementPretService.confirmProlongement(idProlongement, dateValidation);

            model.addAttribute("messageSuccess",
                    "Prolongement confirmé.");
        } catch (Exception e) {
            model.addAttribute("messageError", e.getMessage());
        }
        req.setAttribute("listProlongement", prolongementPretService.getDerniersProlongementsEnAttenteParPret());
        return "form-admin-prolongement";
    }
}
