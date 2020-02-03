package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureQuizButton();
        configureSettingsButton();
        configureHighscoreButton();
    }
    public void configureQuizButton(){
        Button quiz = (Button) findViewById(R.id.button);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuizSelector.class));
            }
        });
    }
    public void configureSettingsButton(){
        Button settings = (Button) findViewById(R.id.button2);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });
    }
    public void configureHighscoreButton(){
        Button highscore = (Button) findViewById(R.id.button3);
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Highscores.class));
            }
        });
    }

}
