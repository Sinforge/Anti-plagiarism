package ru.sinforge.antiplagiarism.Entity;

import org.springframework.stereotype.Component;

@Component
public class TextInputer {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
