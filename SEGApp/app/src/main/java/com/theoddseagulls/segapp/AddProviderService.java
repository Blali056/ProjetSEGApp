package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProviderService extends AppCompatActivity {

    private Spinner serviceName;
    private static  DB_handler mydatabase;
    private ProviderAccount provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addservice);

        serviceName = findViewById(R.id.serviceToAdd);

        mydatabase = new DB_handler(this);

        ArrayList<String> service_options =new ArrayList<>();

        Cursor service = mydatabase.getListContents();

        if(service.getCount() != 0) {       //S'il y a des services dans la base de donnee
            while (service.moveToNext()) {
                service_options.add(service.getString(1) + " - " + service.getString(2) + "$/heure");
            }
        }

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,service_options);
        serviceName.setAdapter(Adapter);

        String providerEmail = getIntent().getStringExtra("EMAIL");
        provider = mydatabase.findProviderAccount(providerEmail);
    }



    public boolean providerServiceExist(){
        boolean exist = true;
        ProviderService serviceInList = mydatabase.findProviderService(serviceName.getSelectedItem().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }

    public void addClick(View view){


        if(providerServiceExist() == true){
            ((TextView)serviceName.getChildAt(0)).setError("Service existant");
        }

        else if(providerServiceExist() == false){
            String service = serviceName.getSelectedItem().toString();
            ProviderService providerService = new ProviderService(provider.getUsername() , service);
            mydatabase.addProviderService(providerService);
            Context context = getApplicationContext();

            Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

            // Passe le username à la prochaine activité
            intent.putExtra("ACCOUNTUSERNAME", provider.getUsername());

            Toast.makeText(context, "Service ajouté",
                    Toast.LENGTH_SHORT).show();

            startActivityForResult(intent, 0);
        }

    }

}
