package ru.sinforge.antiplagiarism.Entity;

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
        System.out.println(document.title());
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
        System.out.println(urls);
        return urls;

    }


    //Составляет один единый текст из найденных на разных сайтах
    public void ResultOfScan(String TextForCheck, ArrayList<String> urls) throws IOException {
        Shingle shingle = new Shingle();
        TextForCheck = shingle.canonize(TextForCheck);
        int size1 = TextForCheck.length();
        ArrayList<Integer> hashes_textcheck = shingle.genShingle(TextForCheck);
        for (String url : urls
        ) {
            String result_text = "";
            Document document = Jsoup.connect(url).userAgent("Yandex").followRedirects(true)
                    .referrer("http://www.google.com").get();
            Elements paragraphs = document.select("p");
            Elements Li_sents = document.select("li");

            for (Element paragraph : paragraphs
            ) {
                result_text += paragraph.text();

            }
            result_text = shingle.canonize(result_text);
            int size2 = result_text.length();
            double koeffic = size2/size1;
            ArrayList<Integer> result_hashes = shingle.genShingle(result_text);
            System.out.println("Сайт: " + url +  "         -----------    Результат проверки: " + (shingle.compare(result_hashes, hashes_textcheck) * koeffic));


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
