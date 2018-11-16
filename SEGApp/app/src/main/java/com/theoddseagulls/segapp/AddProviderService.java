package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProviderService extends AppCompatActivity {
    private EditText serviceName;
    private static  DB_handler mydatabase;
    private ProviderAccount provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addservice);

        serviceName = (EditText) findViewById(R.id.serviceNameAdded);
        mydatabase = new DB_handler(this);
        String providerEmail = getIntent().getStringExtra("EMAIL");
        provider = mydatabase.findProviderAccount(providerEmail);
    }
    public boolean serviceExist(){
        boolean exist = true;
        Service serviceInList = mydatabase.findService(serviceName.getText().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }
    public void addClick(View view){
        if( serviceName.getText().toString().length() == 0 ){
            serviceName.setError("Entrez un service" );
        }
        if(serviceExist() == false ){
            serviceName.setError("Service inexistant");

        }
        else if(serviceExist() == true ){
            String service = serviceName.getText().toString();;
            mydatabase.addProviderService(provider.getUsername(),service);
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouté",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
