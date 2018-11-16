package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addProviderService extends AppCompatActivity {
    private EditText serviceName;
    private String email ;
    private static  DB_handler mydatabase;
    private Service service;
    private ProviderAccount provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addservice);
        email = getIntent().getStringExtra("EMAIL");
        Button serviceListButton = (Button) findViewById(R.id.button7);
        serviceListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),servicelist.class ));
            }
        });

        serviceName = findViewById(R.id.editText10);
        mydatabase = new DB_handler(this);
        provider = (ProviderAccount) mydatabase.findAccount(email);
        // SET THE RIGHT PROVIDER ACCOUNTTTT!!
    }
    public boolean serviceExist (){
        service = mydatabase.findService(serviceName.getText().toString());
        return provider.serviceExist(service.getService());
    }
    public void addClick(View view){
        if( serviceName.getText().length() == 0 ){
            serviceName.setError("Entrez un service" );
        }
        else if(serviceExist() == true ){       // Si le service existe deja
            serviceName.setError("Service existant");
        }
        else{
            service = mydatabase.findService(serviceName.getText().toString());
            provider.addService(service.getService());
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouté",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
