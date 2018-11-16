package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class removeProviderService extends AppCompatActivity {
    private EditText serviceToDelete;
    private static  DB_handler mydatabase;
    private String email ;

    private Service service;
    private ProviderAccount provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removeservice);
        email = getIntent().getStringExtra("EMAIL");
        serviceToDelete = (EditText) findViewById(R.id.editText5);
        mydatabase = new DB_handler(this);
        provider = (ProviderAccount) mydatabase.findAccount(email);
        // SET THE RIGHT PROVIDER ACCOUNTTTT!!
    }
    public boolean serviceExist (){
        service = mydatabase.findService(serviceToDelete.getText().toString());

        return provider.serviceExist(service.getService());
    }
    public void deleteClick(View view){
        if( serviceToDelete.getText().length() == 0 ){       // Si aucun service n'est entré
            serviceToDelete.setError("Entrez un service" );
        }
        else if(serviceExist() == false ){       // Si l'email et le mot de passe sont invalides
            serviceToDelete.setError("Service Inexistant");
        }


        // il faut aussi verifier si

        else{
            service = mydatabase.findService(serviceToDelete.getText().toString());
            provider.removeService(service.getService());
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouté",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
