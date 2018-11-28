package com.theoddseagulls.segapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class book_now extends AppCompatActivity {
    private Spinner availibilities;
    private static  DB_handler mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        availibilities= findViewById(R.id.Availabilities);
        mydatabase = new DB_handler(this);
        ArrayList<String> availabilities_options =new ArrayList<>();
        Cursor service = mydatabase.getListContents();



    }
}
