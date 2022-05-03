package ru.sinforge.antiplagiarism.controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.antiplagiarism.service.Text;
import ru.sinforge.antiplagiarism.service.Check_text;

import java.io.IOException;

@Controller
@ComponentScan
public class MainScreenController {
    private Text text = new Text();
    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }
    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/Text")
    public String Text (Model model) {
        model.addAttribute("InputText", new Text());
        return "index";
    }


    @PostMapping(value="/Text")
    public String Result(@ModelAttribute("InputText") Text text, Model model) {
        Check_text checkText = new Check_text();
        try {
            //TextInputer test = new TextInputer();
            //test.setContent("HELLO VLAD");
            text.setResult_mas(checkText.start(text.getContent()));
            model.addAttribute("result_of_scan", text );


        } catch (IOException e) {
            return "result";
        }
        return "result";
    }
}
