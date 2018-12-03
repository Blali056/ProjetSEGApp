package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private String providerUsername;
    private UserAccount user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        availibilities= findViewById(R.id.Availabilities);
        mydatabase = new DB_handler(this);
        providerUsername = getIntent().getStringExtra("USERNAME");

        ArrayList<String> availabilitiesList = new ArrayList<>();

        samedi = mydatabase.findSamedi(providerUsername);
        dimanche =  mydatabase.findDimanche(providerUsername);
        lundi =  mydatabase.findLundi(providerUsername);
        mardi = mydatabase.findMardi(providerUsername);
        mercredi =  mydatabase.findMercredi(providerUsername);
        jeudi =  mydatabase.findJeudi(providerUsername);
        vendredi = mydatabase.findVendredi(providerUsername);

        user = mydatabase.findUserAccountByUsername(getIntent().getStringExtra("USERNAMEUSER"));

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
        else if ( (samedi.indexOf("DE") >0) && (samedi.indexOf("À") >0) && (dimanche.indexOf("DE") >0) && (dimanche.indexOf("À") >0) && (lundi.indexOf("DE") > 0) && (lundi.indexOf("À") > 0) && (mardi.indexOf("DE") >0) && (mardi.indexOf("À") >0) && (mercredi.indexOf("DE") > 0) && (mercredi.indexOf("À") >0) && (jeudi.indexOf("DE") >0) && (jeudi.indexOf("À") >0) && (vendredi.indexOf("DE") >0) && (vendredi.indexOf("À") >0)){

            availabilitiesList.add("Aucun disponibilité");
        }


        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,availabilitiesList);
        availibilities.setAdapter(Adapter);

    }
    public void bookNow(View view){
        if(availibilities.getSelectedItem().toString().equals("Aucun disponibilité")){
            ((TextView)availibilities.getChildAt(0)).setError("Aucun disponibilité");
        }

        else {
            String selected = availibilities.getSelectedItem().toString();

            ProviderAccount provider = mydatabase.findUsernameProviderAccount(providerUsername);
            String providerFullName = provider.getName() + " " + provider.getLastName();

            user.setAppointment(selected);
            mydatabase.addAppointment(user, providerFullName);

            String[] parts = selected.split(" ");
            String day = parts[0];

            if(day.equals("Samedi")){
                String samedi = "Samedi : DE - À";
                mydatabase.updateSamedi(providerUsername, samedi);
            }

            if(day.equals("Dimanche")){
                String dimanche = "Dimanche : DE - À";
                mydatabase.updateDimanche(providerUsername, dimanche);
            }

            if(day.equals("Lundi")){
                String lundi = "Lundi : DE - À";
                mydatabase.updateLundi(providerUsername, lundi);
            }

            if(day.equals("Mardi")){
                String mardi = "Mardi : DE - À";
                mydatabase.updateMardi(providerUsername, mardi);
            }

            if(day.equals("Mercredi")){
                String mercredi = "Mercredi : DE - À";
                mydatabase.updateMercredi(providerUsername, mercredi);
            }

            if(day.equals("Jeudi")){
                String jeudi = "Jeudi : DE - À";
                mydatabase.updateJeudi(providerUsername, jeudi);
            }

            if(day.equals("Vendredi")){
                String vendredi = "Vendredi : DE - À";
                mydatabase.updateVendredi(providerUsername,vendredi);
            }

            Context context = getApplicationContext();
            Toast.makeText(context, "Rendez-vous pris", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), UserProfil.class);

            intent.putExtra("PROVIDERNAME", providerFullName);

            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);


        }

    }



}