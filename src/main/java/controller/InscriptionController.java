package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.InscriptionEntity;
import entities.TypeAccountEntity;
import entities.TypeAccountEntity;
import entities.UsersEntity;
import jakarta.servlet.http.HttpServletRequest;
import service.InscriptionService;
import service.TypeAccountService;
import service.UsersService;
import java.util.List;
import org.springframework.ui.Model;

@Controller
public class InscriptionController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private TypeAccountService typeAccountService;

    @RequestMapping(value = "/inscription", method = RequestMethod.GET)
    public String showInscriptionForm(Model model) {
        model.addAttribute("listAccountType", typeAccountService.getAllTypeAccounts());
        return "home";
    }

    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String handleCreateUsers(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String motdepasse = request.getParameter("motdepasse");
        Integer idTypeAccount = Integer.parseInt(request.getParameter("typeaccount"));

        try {
            TypeAccountEntity typeAccount = typeAccountService.getTypeAccountById(idTypeAccount);
            UsersEntity user = new UsersEntity();
            user.setMotDePasse(motdepasse);
            user.setUserName(username);
            user.setTypeAccount(typeAccount);

            usersService.save(user);
            return "login";
        } catch (RuntimeException e) {
            List<TypeAccountEntity> types = typeAccountService.getAllTypeAccounts();
            model.addAttribute("listAccountType", types);
            model.addAttribute("error", "Type de compte invalide");
            return "home";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  handleLoginUsers(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String motdepasse = request.getParameter("motdepasse");

        try {
            if (usersService.authenticate(username, motdepasse)) {
                return "accueil";
            }

        } catch (RuntimeException e) {
            model.addAttribute("error", "Type de compte invalide");
            return "home";
        }
    }
}
