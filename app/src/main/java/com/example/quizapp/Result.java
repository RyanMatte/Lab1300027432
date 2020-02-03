package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView t1 = (TextView)findViewById(R.id.textView7);
        TextView t2 = (TextView)findViewById(R.id.textView11);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        float m = 100*(Float.parseFloat(databaseAccess.findCurrentScore()) / Float.parseFloat(databaseAccess.findMaxQuiz()));

        if ( m > Integer.parseInt(databaseAccess.findPassingGrade())){
            t1.setText("you passed with a :");
            t2.setText(m + "%");
        } else {
            t1.setText("You failed with a :");
            t2.setText(m + "%");
        }
        databaseAccess.insertScore(m);
        databaseAccess.close();
        goBack();
    }
    public void goBack(){
        Button quit = (Button)findViewById(R.id.button10);
        quit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View w){
                                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                        databaseAccess.open();
                                        databaseAccess.updateScore(0);

                                        startActivity(new Intent(Result.this, MainActivity.class));
                                    }
                                }

        );
    }
}
