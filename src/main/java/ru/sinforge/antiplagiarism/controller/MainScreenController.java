package ru.sinforge.antiplagiarism.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.antiplagiarism.domain.Result;
import ru.sinforge.antiplagiarism.domain.User;
import ru.sinforge.antiplagiarism.repos.ResultRepo;
import ru.sinforge.antiplagiarism.service.Text;
import ru.sinforge.antiplagiarism.service.Check_text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ComponentScan
public class MainScreenController {
    @Autowired
    private ResultRepo resultRepo;

    private Text text = new Text();


    @GetMapping(value = "/")
    public String home(Model model) {
        return "main";
    }



    @GetMapping(value = "/auth")
    public String auth(Model model) {
        return "auth";
    }


    @GetMapping(value = "/service")
    public String Text (Model model) {
        model.addAttribute("InputText", new Text());
        return "service";
    }


    @PostMapping(value="/service")
    public String Result(@AuthenticationPrincipal User user, @ModelAttribute("InputText") Text text, Model model) {
        Check_text checkText = new Check_text();
        try {
            Object[] res_of_ch = checkText.start(text.getContent());
            double res = 100 - (int)res_of_ch[0];

            //Определяем дату
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd");
            Result result = new Result(res, user,  formatForDateNow.format(dateNow));


            resultRepo.save(result);
            model.addAttribute("find_url", (ArrayList<String>) res_of_ch[1]);

            text.setContent("" +result.getRes());
            model.addAttribute("result_of_scan", text );


        } catch (IOException e) {
            return "result";
        }
        return "result";
    }

    @GetMapping("/lk")
    public String PA(@AuthenticationPrincipal User user, Model model) {
        List<Result> results = resultRepo.findByUser(user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("results", results);
        return "lk";
    }

}
