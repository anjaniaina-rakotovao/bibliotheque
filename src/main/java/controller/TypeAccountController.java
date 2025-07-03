package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.TypeAccountEntity;
import jakarta.servlet.http.HttpServletRequest;
import service.TypeAccountService;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class TypeAccountController {
    @Autowired
    private TypeAccountService typeAccountService;

    @GetMapping("/")
    public String listerAccountType(Model model){
        List<TypeAccountEntity> types = typeAccountService.getAllTypeAccounts();
        model.addAttribute("listAccountType", types);
        return "home";
    }
}