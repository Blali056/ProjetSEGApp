package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditProviderProfil extends AppCompatActivity {

    private TextView email;
    private TextView phone;
    private TextView streetNumber;
    private TextView streetName;
    private TextView postalCode;
    private TextView city;
    private TextView province;
    private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_provider_profil);

        email = (TextView) findViewById(R.id.providerEmail);
        phone = (TextView) findViewById(R.id.phoneNumber);
        streetNumber = (TextView) findViewById(R.id.streetNumber);
        streetName = (TextView) findViewById(R.id.streetName);
        postalCode = (TextView) findViewById(R.id.postalCode);
        city = (TextView) findViewById(R.id.city);
        province = (TextView) findViewById(R.id.province);

        // Ecris l'email du account
        email.setText( getIntent().getStringExtra("EMAIL") );
        final String userEmail = getIntent().getStringExtra("EMAIL");




        Button btn = (Button) findViewById(R.id.addService);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), AddProviderService.class);

                    intent.putExtra("EMAIL", userEmail);

                    startActivityForResult(intent, 0);

            }
        });

    }

}
