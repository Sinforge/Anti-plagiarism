package ru.sinforge.antiplagiarism.controller;


import lombok.extern.java.Log;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.antiplagiarism.Entity.TextInputer;
import ru.sinforge.antiplagiarism.service.Check_text;

import java.io.IOException;

@Controller
@ComponentScan
public class MainScreenController {
    private TextInputer textResult = new TextInputer();
    @RequestMapping(value = "/Post_text", method = RequestMethod.GET)
    public String PostText (Model model) {
        model.addAttribute("InputText", new TextInputer());

        return "index";
    }
    @RequestMapping(value="/Post_text", method=RequestMethod.POST)
    public String Result(@ModelAttribute TextInputer textInputer,Model model) {
        Check_text checkText = new Check_text();
        try {
            textResult.setContent(checkText.start(textInputer.getContent()));
            System.out.println(textResult.getContent());
            model.addAttribute("result_of_scan", textResult );
        } catch (IOException e) {
        }
        return "result";
    }
}
