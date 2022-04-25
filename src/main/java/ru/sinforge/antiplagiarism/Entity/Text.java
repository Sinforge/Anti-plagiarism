package ru.sinforge.antiplagiarism.Entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Text {
    private String content;

    private ArrayList<String> result_mas = new ArrayList<>(10);

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getResult_mas() {
        return result_mas;
    }

    public void setResult_mas(ArrayList<String> result_mas) {
        this.result_mas = result_mas;
    }
}
