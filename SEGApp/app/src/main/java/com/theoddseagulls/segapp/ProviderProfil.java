package com.theoddseagulls.segapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProviderProfil extends AppCompatActivity {
    private String providerUsername;
    private TextView email;
    private static DB_handler myDataBase;
    private ProviderAccount provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profil);

        myDataBase = new DB_handler(this);

        email = (TextView) findViewById(R.id.providerEmail);
        // Ecris l'email du account
        providerUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
        email.setText( myDataBase.findUsernameAccount(providerUsername).getEmail() );
        provider = myDataBase.findProviderAccount(providerUsername);
        ListView listView = (ListView) findViewById(R.id.providerServicesList);
        ArrayList<String> serviceList = new ArrayList<>();
        ArrayList<String> providerServiceList = provider.providerServiceList();
        providerServiceList.add("string1");
            int i = 1;
            while(i < providerServiceList.size()){
                serviceList.add(providerServiceList.get(i));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,serviceList);
                listView.setAdapter(listAdapter);
            }

    }


    public void editProfilClick(View view){
        Intent intent = new Intent(getApplicationContext() , EditProviderProfil.class);
        intent.putExtra("EMAIL" ,  myDataBase.findUsernameAccount(providerUsername).getEmail());
        startActivityForResult(intent , 0);
    }
}
