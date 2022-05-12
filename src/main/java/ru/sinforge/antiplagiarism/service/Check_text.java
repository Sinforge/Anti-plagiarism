package ru.sinforge.antiplagiarism.service;

import java.io.IOException;
import java.util.ArrayList;

public class Check_text {
    public double start(String Usertext) throws IOException {
        String[] Sentences = Usertext.split("\\.");
        double res = 0.0;
        for(int i = 0 ; i < Sentences.length; i++) {
            GoogleSearcher googleSearcher = new GoogleSearcher();//Гугл поисковик
            ArrayList<String> UrlFoundedInGoogle = googleSearcher.findUrlsBySentence(Usertext);//находим ссылки по тексту;
            res += googleSearcher.ResultOfScan(Usertext, UrlFoundedInGoogle);//Найденный текст на страницах
        }
        return res / Sentences.length;
    }
}
