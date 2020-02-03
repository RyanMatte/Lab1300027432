package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        configureSubmitButton();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

    }
    public void configureSubmitButton(){
        Button submit = (Button)findViewById(R.id.button9);
        final EditText questions = (EditText)findViewById(R.id.textView6);
        final EditText passinggrade = (EditText)findViewById(R.id.textView8);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View w){
                if(questions.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter an amount of questions between 1-20", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if(passinggrade.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter the passing grade between 0-100", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if (passinggrade.getText().toString().equals("") && questions.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter an amount of questions between 1-20", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if(Integer.parseInt(questions.getText().toString()) > 11 || Integer.parseInt(passinggrade.getText().toString()) < 0){
                    Toast.makeText(getApplicationContext(), "Please enter an amount of questions between  1-20", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else if(Integer.parseInt(passinggrade.getText().toString()) > 100 || Integer.parseInt(passinggrade.getText().toString()) < 0){
                    Toast.makeText(getApplicationContext(), "Please enter the passing grade between 0-100", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    databaseAccess.updateOptions( passinggrade.getText().toString(), questions.getText().toString());
                    databaseAccess.close();
                    startActivity(new Intent(Settings.this, MainActivity.class));
                }
            }
        }
        );
    }
}
