package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.LivreEntity;
import jakarta.servlet.http.HttpServletRequest;
import service.LivreService;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class LivreController {
    @Autowired
    private LivreService tagService;

    @RequestMapping(value = "/listelivre", method = RequestMethod.GET)
    public String listerLivre(Model model){
        List<LivreEntity> tags = tagService.findAll();
        model.addAttribute("listLivre", tags);
        return "list-livre";
    }
}
