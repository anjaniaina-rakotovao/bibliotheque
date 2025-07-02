package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.TagEntity;
import jakarta.servlet.http.HttpServletRequest;
import service.TagService;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String listerTag(Model model){
        List<TagEntity> tags = tagService.findAll();
        model.addAttribute("listTag", tags);
        return "home";
    }
}
