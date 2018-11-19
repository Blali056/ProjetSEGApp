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

import java.security.Provider;
import java.util.ArrayList;

public class ProviderProfil extends AppCompatActivity {

    private TextView email;
    private TextView address;
    private TextView phone;
    private TextView company;
    private TextView licence;
    private TextView licenceTitle;
    private static DB_handler myDataBase;
    private String providerUsername;
    private ProviderAccount provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profil);

        email = (TextView) findViewById(R.id.providerEmail);
        address = (TextView) findViewById(R.id.providerAddress);
        phone = (TextView) findViewById(R.id.providerPhone);
        company = (TextView) findViewById(R.id.providerCompany);
        licence = (TextView) findViewById(R.id.providerLicense);
        licenceTitle = (TextView) findViewById(R.id.license);

        myDataBase = new DB_handler(this);

        providerUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
        provider = myDataBase.findUsernameProviderAccount(providerUsername);

        email.setText(provider.getEmail());
        address.setText(provider.getAddress());
        phone.setText(provider.getPhone());
        company.setText(provider.getCompany());
        licence.setText(provider.getLicence());

        if(provider.getLicence() == null){
           licence.setAlpha(0.0f);
           licenceTitle.setAlpha(0.0f);
        }


        ListView listView = (ListView) findViewById(R.id.providerServicesList);
        myDataBase = new DB_handler(this);
        ArrayList<String> serviceList = new ArrayList<>();
        Cursor providerservice = myDataBase.getProviderListContents();
        if(providerservice.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(providerservice.moveToNext()) {
                if(providerservice.getString(1).equals(providerUsername)){
                    serviceList.add(providerservice.getString(2));
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,serviceList);
                    listView.setAdapter(listAdapter);
                }
            }
        }

    }

    public void editProfilClick(View view){

        Intent intent = new Intent(getApplicationContext(), EditProviderProfil.class);

        // Passe l'email à la prochaine activité
        String email = myDataBase.findUsernameAccount(providerUsername).getEmail();
        intent.putExtra("EMAIL", email);

        startActivityForResult(intent, 0);

    }

}
