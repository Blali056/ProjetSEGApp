package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    }

    public void editProfilClick(View view){

        Intent intent = new Intent(getApplicationContext(), EditProviderProfil.class);

        // Passe le username à la prochaine activité
        String email = myDataBase.findUsernameAccount(providerUsername).getEmail();
        intent.putExtra("EMAIL", email);

        startActivityForResult(intent, 0);

    }
}
