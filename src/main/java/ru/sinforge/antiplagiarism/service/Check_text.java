package ru.sinforge.antiplagiarism.service;

import ru.sinforge.antiplagiarism.Entity.GoogleSearcher;

import java.io.IOException;
import java.util.ArrayList;

public class Check_text {
    public String start(String Usertext) throws IOException {
        GoogleSearcher googleSearcher = new GoogleSearcher();//Гугл поисковик
        ArrayList<String> UrlFoundedInGoogle = googleSearcher.findUrlsBySentence(Usertext);//находим ссылки по тексту;
        return googleSearcher.ResultOfScan(Usertext, UrlFoundedInGoogle);//Найденный текст на страницах
    }
}
