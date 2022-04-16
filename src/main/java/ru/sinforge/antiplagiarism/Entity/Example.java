package ru.sinforge.antiplagiarism.Entity;

public class Example {
    private int age;
    private String name;
    public Example(int age, String name) {
        this.age = age;
        this.name=  name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
