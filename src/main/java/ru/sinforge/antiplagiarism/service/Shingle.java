package ru.sinforge.antiplagiarism.service;

import java.util.ArrayList;


public class Shingle {
    private static final String[] STOP_SYMBOLS = {".", ",", "!", "?", ":", ";", "-", "\\", "/", "*", "(", ")", "+", "@",
            "#", "$", "%", "^", "&", "=", "'", "\"", "[", "]", "{", "}", "|"};
    private static final String[] STOP_WORDS_RU = {"это", "как", "так", "и", "в", "над", "к", "до", "не", "на", "но", "за",
            "то", "с", "ли", "а", "во", "от", "со", "для", "о", "же", "ну", "вы",
            "бы", "что", "кто", "он", "она"};

    private static final int SHINGLE_LEN = 2;


    public String canonize(String str) {
        for (String stopSymbol : STOP_SYMBOLS) {
            str = str.replace(stopSymbol, "");
        }

        for (String stopWord : STOP_WORDS_RU) {
            str = str.replace(" " + stopWord + " ", " ");
        }

        return str;
    }

    public ArrayList<Integer> genShingle(String strNew) {
        ArrayList<Integer> shingles = new ArrayList<Integer>();
        String str = canonize(strNew.toLowerCase());
        String[] words = str.split(" ");
        int shinglesNumber = words.length - SHINGLE_LEN;

        //Create all shingles
        for (int i = 0; i <= shinglesNumber; i++) {
            StringBuilder shingle = new StringBuilder();

            //Create one shingle
            for (int j = 0; j < SHINGLE_LEN; j++) {
                shingle.append(words[i + j]).append(" ");
            }

            shingles.add(shingle.toString().hashCode());
        }

        return shingles;
    }


    public double compare(ArrayList<Integer> textShingles1New, ArrayList<Integer> textShingles2New) {
        //textShingles1New and textShingles2New equals null bug fix
        if (textShingles1New == null || textShingles2New == null) return 0.0;

        int textShingles1Number = textShingles1New.size();
        int textShingles2Number = textShingles2New.size();

        double similarShinglesNumber = 0;

        for (Integer integer : textShingles1New) {
            for (Integer value : textShingles2New) {
                if (integer.equals(value)) {
                    similarShinglesNumber++;
                    break;
                }
            }
        }

        return ((similarShinglesNumber / textShingles1Number) * 100);
    }
}