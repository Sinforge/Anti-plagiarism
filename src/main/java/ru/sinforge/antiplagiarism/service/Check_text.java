package ru.sinforge.antiplagiarism.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Check_text {
    public Object[] start(String Usertext) throws IOException {
        Object[] res1 = new Object[2];
        String[] Sentences = Usertext.split("\\.");
        Integer res = 0;
        ArrayList<String> urls = new ArrayList<>();
        for(int i = 0 ; i < Sentences.length; i++) {
            GoogleSearcher googleSearcher = new GoogleSearcher();//Гугл поисковик
            ArrayList<String> UrlFoundedInGoogle = googleSearcher.findUrlsBySentence(Usertext);//находим ссылки по тексту;
            Object[] result = googleSearcher.ResultOfScan(Usertext, UrlFoundedInGoogle);//Найденный текст на страницах;
            res += (int) result[0];
            urls.addAll((ArrayList<String>)result[1]);
        }
        res1[0] = (int)(res / Sentences.length);
        res1[1] = urls;
        return res1;
    }
}
