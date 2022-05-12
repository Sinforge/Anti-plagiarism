package ru.sinforge.antiplagiarism.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sinforge.antiplagiarism.domain.Role;
import ru.sinforge.antiplagiarism.domain.User;
import ru.sinforge.antiplagiarism.repos.UserRep;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRep userRep;


    @GetMapping("/registration")
    public String registration() {
        return "reg";
    }

    @PostMapping("/registration")
    public String AddUser(User user, Map<String, Object> model) {
        User userFromDb = userRep.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists");
            return "reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "auth";
    }

}
