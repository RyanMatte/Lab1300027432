package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);


    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public boolean updateOptions(String pass, String percent) {
        open();
        String query = "UPDATE OPTIONS SET passinggrade = ?, amount = ? ";
        Cursor cursor = db.rawQuery(query, new String[]{pass, percent});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }
    public Cursor GetListContents(String quiznumber){
        open();
        Cursor data = db.rawQuery("SELECT * FROM QUIZES WHERE quiznumber = ?",  new String[]{quiznumber});
        return data;
    }
    public String findQN(){
        open();
        String empty = "";
        String query = "SELECT * FROM CURRENT";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            return value;
        }
        return empty;
    }
    public boolean updateQN(Integer QN){
        open();
        String query = "UPDATE CURRENT SET QN = ?";
        Cursor cursor = db.rawQuery(query, new String[]{QN.toString()});
        if (cursor.getCount() > 0){
            return true;
        } else return false;
    }
    public String findQuiz(){
        open();
        String empty = "";
        String query = "SELECT * FROM QUIZSELECTOR";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            c.close();
            return value;
        }
        c.close();
        return empty;
    }
    public boolean chooseQuiz(Integer QN){
        open();
        String query = "UPDATE QUIZSELECTOR SET selected = ?";
        Cursor cursor = db.rawQuery(query, new String[]{QN.toString()});
        if (cursor.getCount() > 0){
            cursor.close();
            return true;

        } else {
            cursor.close();
            return false;
        }
    }
    public String findMaxQuiz(){
        open();
        String empty = "";
        String query = "SELECT amount FROM OPTIONS";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            return value;
        }
        c.close();
        return empty;
    }
    public String findPassingGrade(){
        open();
        String empty = "";
        String query = "SELECT passinggrade FROM OPTIONS";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            return value;
        }
        c.close();
        return empty;
    }
    public String findCurrentScore(){
        open();
        String empty = "";
        String query = "SELECT amountcorrect FROM SCORES";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            c.close();
            return value;
        }
        c.close();
        return empty;
    }
    public boolean updateScore(Integer Score){
        open();
        String query = "UPDATE SCORES SET amountcorrect = ?";
        Cursor cursor = db.rawQuery(query, new String[]{Score.toString()});
        if (cursor.getCount() > 0){
            return true;
        } else return false;
    }
    public boolean insertScore(Float score) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("scores", score);
        long result = db.insert("HIGHSCORES", null, contentValues);
        close();
        if (result == -1)
            return false;

        else
            return true;
    }
    public String findHighScore(){
        open();
        String empty = "";
        String query = "SELECT MAX(scores) FROM HIGHSCORES";
        Cursor c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()){
            String value = c.getString(0);
            c.close();
            return value;
        }
        c.close();
        return empty;
    }
}