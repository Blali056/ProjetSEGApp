package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProviderProfil extends AppCompatActivity {

    private TextView email;
    private static DB_handler myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profil);

        email = (TextView) findViewById(R.id.providerEmail);

        myDataBase = new DB_handler(this);

        // Ecris l'email du account
        String providerUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
        email.setText( myDataBase.findUsernameAccount(providerUsername).getEmail() );

    }

    public void editProfilClick(View view){
        startActivity(new Intent(getApplicationContext(),EditProviderProfil.class ));
    }
}
