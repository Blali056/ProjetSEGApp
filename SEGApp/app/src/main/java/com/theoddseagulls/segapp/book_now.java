package com.theoddseagulls.segapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class book_now extends AppCompatActivity {
    private Spinner availibilities;
    private static  DB_handler mydatabase;
    private String samedi;
    private String dimanche;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        availibilities= findViewById(R.id.Availabilities);
        mydatabase = new DB_handler(this);
        String providerUsername = getIntent().getStringExtra("USERNAME");

        ArrayList<String> availabilitiesList = new ArrayList<>();

        samedi = mydatabase.findSamedi(providerUsername);
        dimanche =  mydatabase.findDimanche(providerUsername);
        lundi =  mydatabase.findLundi(providerUsername);
        mardi = mydatabase.findMardi(providerUsername);
        mercredi =  mydatabase.findMercredi(providerUsername);
        jeudi =  mydatabase.findJeudi(providerUsername);
        vendredi = mydatabase.findVendredi(providerUsername);


        if( samedi.indexOf("DE") <0 && samedi.indexOf("À") <0) {
            availabilitiesList.add(samedi);
        }

        if(dimanche.indexOf("DE") <0 && dimanche.indexOf("À") <0) {
            availabilitiesList.add(dimanche);
        }

        if(lundi.indexOf("DE") <0 && lundi.indexOf("À") <0) {
            availabilitiesList.add(lundi);
        }

        if(mardi.indexOf("DE") <0 && mardi.indexOf("À") <0) {
            availabilitiesList.add(mardi);
        }

        if(mercredi.indexOf("DE") <0 && mercredi.indexOf("À") <0) {
            availabilitiesList.add(mercredi);
        }

        if(jeudi.indexOf("DE") <0 && jeudi.indexOf("À") <0) {
            availabilitiesList.add(jeudi);
        }

        if(vendredi.indexOf("DE") <0 && vendredi.indexOf("À") <0) {
            availabilitiesList.add(vendredi);
        }
        else{
            availabilitiesList.add("NO Availabilities ");
        }


        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,availabilitiesList);
        availibilities.setAdapter(Adapter);

    }
    public void bookNow(View view){
        if(availibilities.getSelectedItem().toString().equals("NO Availabilities")){
            ((TextView)availibilities.getChildAt(0)).setError("NO Availabilities");
        }

    }



}
