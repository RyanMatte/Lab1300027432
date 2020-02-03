package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class questions extends AppCompatActivity {
    List<Question> questionsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        submitAnswer();
        quitQuiz();

        RadioButton rb1 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton rb2 = (RadioButton)findViewById(R.id.radioButton3);
        RadioButton rb3 = (RadioButton)findViewById(R.id.radioButton4);
        RadioButton rb4 = (RadioButton)findViewById(R.id.radioButton5);

        TextView tv = (TextView)findViewById(R.id.textView5);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Cursor data = databaseAccess.GetListContents(databaseAccess.findQuiz());
        while(data.moveToNext()){
            questionsList.add(new Question(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7)));
        }
        if(Integer.parseInt(databaseAccess.findQN()) == 10){
            databaseAccess.updateQN(0);
            startActivity(new Intent(questions.this, Result.class));
        }
        if(Integer.parseInt(databaseAccess.findMaxQuiz()) == Integer.parseInt(databaseAccess.findQN()) ){
            Toast.makeText(getApplicationContext(), "The Test is Complete", Toast.LENGTH_SHORT).show();
            databaseAccess.updateQN(0);
            startActivity(new Intent(questions.this, Result.class));
        }
        rb1.setText(questionsList.get(Integer.parseInt(databaseAccess.findQN())).a);
        rb2.setText(questionsList.get(Integer.parseInt(databaseAccess.findQN())).b);
        rb3.setText(questionsList.get(Integer.parseInt(databaseAccess.findQN())).c);
        rb4.setText(questionsList.get(Integer.parseInt(databaseAccess.findQN())).d);
        tv.setText(questionsList.get(Integer.parseInt(databaseAccess.findQN())).question);
        databaseAccess.close();
    }
    public void submitAnswer(){
        final RadioGroup question = (RadioGroup)findViewById(R.id.radioGroup);
        Button submit = (Button)findViewById(R.id.button7);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w){
                if (question.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    RadioButton a = (RadioButton)findViewById(R.id.radioButton2);
                    RadioButton b = (RadioButton)findViewById(R.id.radioButton3);
                    RadioButton c = (RadioButton)findViewById(R.id.radioButton4);
                    RadioButton d = (RadioButton)findViewById(R.id.radioButton5);
                    if(a.isChecked() && questionsList.get(Integer.parseInt(databaseAccess.findQN())).correctans.equals("a")){
                        int v = Integer.parseInt(databaseAccess.findQN()) +1;
                        int s = Integer.parseInt(databaseAccess.findCurrentScore())+ 1;
                        databaseAccess.updateScore(s);
                        databaseAccess.updateQN(v);
                        databaseAccess.close();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                    }
                    else if(b.isChecked() && questionsList.get(Integer.parseInt(databaseAccess.findQN())).correctans.equals("b")){
                        int v = Integer.parseInt(databaseAccess.findQN()) +1;
                        databaseAccess.updateQN(v);
                        int s = Integer.parseInt(databaseAccess.findCurrentScore())+ 1;
                        databaseAccess.updateScore(s);
                        databaseAccess.close();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                    }
                    else if(c.isChecked() && questionsList.get(Integer.parseInt(databaseAccess.findQN())).correctans.equals("c")){
                        int v = Integer.parseInt(databaseAccess.findQN()) +1;
                        databaseAccess.updateQN(v);
                        int s = Integer.parseInt(databaseAccess.findCurrentScore())+1;
                        databaseAccess.updateScore(s);
                        databaseAccess.close();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                    }
                    else if(d.isChecked() && questionsList.get(Integer.parseInt(databaseAccess.findQN())).correctans.equals("d")){
                        int v = Integer.parseInt(databaseAccess.findQN()) +1;
                        databaseAccess.updateQN(v);
                        int s = Integer.parseInt(databaseAccess.findCurrentScore())+ 1;
                        databaseAccess.updateScore(s);
                        databaseAccess.close();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                    } else{
                        int v = Integer.parseInt(databaseAccess.findQN()) +1;
                        databaseAccess.updateQN(v);
                        databaseAccess.close();
                        finish();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );

    }
    public void quitQuiz(){
        Button quit = (Button)findViewById(R.id.button8);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w){
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                databaseAccess.updateQN(0);
                databaseAccess.close();
                startActivity(new Intent(questions.this, MainActivity.class));
            }
        }

        );
    }
}
