package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Highscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        configureHighScoreBack();
        TextView hs = (TextView)findViewById(R.id.textView4);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        hs.setText(databaseAccess.findHighScore());
        databaseAccess.close();
    }
    public void configureHighScoreBack(){
        Button backbutton = (Button)findViewById(R.id.button6);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Highscores.this, MainActivity.class));
            }
        });
    }
}
