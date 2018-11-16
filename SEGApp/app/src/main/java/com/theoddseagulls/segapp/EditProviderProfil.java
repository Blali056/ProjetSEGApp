package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditProviderProfil extends AppCompatActivity {
    private TextView email;
    private String providerEmail;
    private static DB_handler myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_provider_profil);
        myDataBase = new DB_handler(this);
        email = (TextView) findViewById(R.id.providerEmail);
        email.setText(getIntent().getStringExtra("EMAIL"));
        providerEmail = getIntent().getStringExtra("EMAIL");

    }

    public void addClick(View view){
        Intent intent = new Intent(getApplicationContext() , addProviderService.class);
        intent.putExtra("EMAIL" ,providerEmail);
        startActivityForResult(intent , 0);
    }
    public void deleteClick(View view){
        Intent intent = new Intent(getApplicationContext() , removeProviderService.class);
        intent.putExtra("EMAIL" ,providerEmail);
        startActivityForResult(intent , 0);
    }

}
