package com.theoddseagulls.segapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class servicelist extends AppCompatActivity {

    private DB_handler my_database;
    private ListView ServiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        ListView listView = (ListView) findViewById(R.id.list);
        my_database = new DB_handler(this);

        ArrayList<String> serviceList = new ArrayList<>();
        Cursor service = my_database.getListContents();

        if(service.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(service.moveToNext()) {
                serviceList.add(service.getString(1) + "                              " + service.getString(2)+"$/heure");
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,serviceList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
