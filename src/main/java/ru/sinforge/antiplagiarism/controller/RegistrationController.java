package ru.sinforge.antiplagiarism.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sinforge.antiplagiarism.domain.User;
import ru.sinforge.antiplagiarism.service.UserService;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String registration() {
        return "reg";
    }

    @PostMapping("/registration")
    public String AddUser(User user, Map<String, Object> model) {
        if (!userService.addUser(user)) {
            model.put("message", "User exists");
            return "reg";
        }
        return "auth";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("ans", "User successfully activated");
        }
        else {
            model.addAttribute("ans", "Activation code is not found");

        }
        return "auth";

    }

}
