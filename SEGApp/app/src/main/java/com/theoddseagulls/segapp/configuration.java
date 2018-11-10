package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }

    public void addServiceBtnClick(View view) {

        // Ouvre l'activite pour ajouter un service
        Intent intent = new Intent(getApplicationContext(), AddService.class);
        startActivityForResult (intent,0);
    }

    public void removeServiceBtnClick(View view) {

        // Ouvre l'activite pour supprimer un service
        Intent intent = new Intent(getApplicationContext(), DeleteService.class);
        startActivityForResult (intent,0);
    }

    public void modifyServiceBtnClick(View view) {

        // Ouvre l'activite pour modifier un service
        Intent intent = new Intent(getApplicationContext(), ModifySErvice.class);
        startActivityForResult (intent,0);
    }
    
    public void viewServiceBtnClick(View view) {

        // Ouvre l'activite pour voir la liste de service
        Intent intent = new Intent(getApplicationContext(), servicelist.class);
        startActivityForResult (intent,0);
    }
}
