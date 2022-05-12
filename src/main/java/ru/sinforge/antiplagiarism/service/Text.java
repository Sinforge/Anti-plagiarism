package ru.sinforge.antiplagiarism.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Text {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
