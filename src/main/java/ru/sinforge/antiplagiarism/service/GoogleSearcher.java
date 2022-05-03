package ru.sinforge.antiplagiarism.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//Класс для взаимодействия с поисковой системой
public class GoogleSearcher {


    //Поиск страниц по тексту
    public ArrayList<String> findUrlsBySentence(String text) throws IOException {
        String search_url = "https://www.google.com/search?q=" + text;
        Document document = Jsoup.connect(search_url).userAgent("Chrome/4.0.249.0 Safari/532.5").followRedirects(true)
                .referrer("http://www.google.com").get();
        document.charset(StandardCharsets.UTF_8);
        Elements hrefs = document.select("a[href]");
        ArrayList<String> urls = new ArrayList<>();
        for (Element href : hrefs
        ) {
            if (href.attr("href").contains("/url?q=") & !href.attr("href").contains("pdf") & href.attr("href").length() < 200) {
                int last_ch = (href.attr("href").indexOf("&sa"));
                char[] result = new char[last_ch - 7];
                href.attr("href").getChars(7, last_ch, result, 0);
                urls.add(String.valueOf(result));
            }

        }
        return urls;

    }

    //Составляет один единый текст из найденных на разных сайтах
    public ArrayList<String> ResultOfScan(String TextForCheck, ArrayList<String> urls) throws IOException {
        Shingle shingle = new Shingle();
        ArrayList<String> result = new ArrayList<>();
        int size1 = TextForCheck.length();
        ArrayList<Integer> hashes_textcheck = shingle.genShingle(TextForCheck);
        int number = 0;
        for (String url : urls
        ) {
            number ++;
            String result_text = "";
            Document document;
            try {
                document = Jsoup.connect(url).userAgent("Yandex").followRedirects(true)
                        .referrer("http://www.google.com").get();
            } catch (IOException e) {
                continue;
            }
            Elements paragraphs = document.select("p");
            Elements Li_sents = document.select("li");

            for (Element paragraph : paragraphs
            ) {
                result_text += " " + paragraph.text();

            }
            result_text = shingle.canonize(result_text);

            int size2 = result_text.length();
            ArrayList<Integer> result_hashes = shingle.genShingle(result_text);
            result.add(("Сайт: " + url + "------Процент заимствований: " + (shingle.compare(hashes_textcheck, result_hashes))));
            if(result.size() == 3) {
                break;
            }
        }
        return result;
    }
}
