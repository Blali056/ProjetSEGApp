package com.theoddseagulls.segapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProviderProfil extends AppCompatActivity {

    private TextView email;
    private TextView address;
    private static DB_handler myDataBase;
    private String providerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profil);

        email = (TextView) findViewById(R.id.providerEmail);
        address = (TextView) findViewById(R.id.providerAddress);

        myDataBase = new DB_handler(this);

        // Ecris l'email du account
        providerUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
        email.setText( myDataBase.findUsernameAccount(providerUsername).getEmail() );

        ListView listView = (ListView) findViewById(R.id.providerServicesList);
        myDataBase = new DB_handler(this);
        ArrayList<String> serviceList = new ArrayList<>();
        Cursor providerservice = myDataBase.getListContents();
        if(providerservice.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(providerservice.moveToNext()) {
                if(providerservice.getString(1) == providerUsername){
                    serviceList.add("                              " + providerservice.getString(2)+"$/heure");
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,serviceList);
                    listView.setAdapter(listAdapter);
                }
            }
        }

    }

    public void editProfilClick(View view){

        Intent intent = new Intent(getApplicationContext(), EditProviderProfil.class);

        // Passe le username à la prochaine activité
        String email = myDataBase.findUsernameAccount(providerUsername).getEmail();
        intent.putExtra("EMAIL", email);

        startActivityForResult(intent, 0);

    }
}
