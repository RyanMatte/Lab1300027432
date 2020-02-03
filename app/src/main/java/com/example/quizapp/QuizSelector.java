package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selector);
        chooseQuizOne();
        chooseQuizTwo();

    }
    public void chooseQuizOne(){
        Button quiz1 = (Button)findViewById(R.id.button4);
        quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                databaseAccess.chooseQuiz(1);
                databaseAccess.close();
                startActivity(new Intent(QuizSelector.this, questions.class));
            }
        });
    }
    public void chooseQuizTwo(){
        Button quiz2 = (Button)findViewById(R.id.button5);
        quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                databaseAccess.chooseQuiz(2);
                databaseAccess.close();
                startActivity(new Intent(QuizSelector.this, questions.class));
            }
        });
    }
}
