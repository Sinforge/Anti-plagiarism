package ru.sinforge.antiplagiarism.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sinforge.antiplagiarism.Entity.Example;

@Controller
public class MainScreenController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new Example(20, "Vlad"));
        return "index";
    }
}
