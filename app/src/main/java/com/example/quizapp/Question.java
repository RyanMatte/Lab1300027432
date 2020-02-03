package com.example.quizapp;

public class Question {
    String questionnumber;
    String question;
    String a;
    String b;
    String c;
    String d;
    String correctans;
    public Question(String questionnumber, String question, String a, String b, String c, String d, String correctans) {
        this.questionnumber = questionnumber;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctans = correctans;
    }
}
