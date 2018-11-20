package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteProviderService extends AppCompatActivity {

    private Spinner serviceToDelete;
    private static  DB_handler mydatabase;
    private String service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removeservice);

        serviceToDelete = findViewById(R.id.serviceToDelete);

        mydatabase = new DB_handler(this);

        ArrayList<String> service_options =new ArrayList<>();

        Cursor service = mydatabase.getProviderListContents();

        if(service.getCount() != 0) {       //S'il y a des services dans la base de donnee
            while (service.moveToNext()) {
                service_options.add(service.getString(2));
            }
        }

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,service_options);
        serviceToDelete.setAdapter(Adapter);
    }

    public boolean serviceExist (){
        boolean exist = true;
        ProviderService serviceInList = mydatabase.findProviderService(serviceToDelete.getSelectedItem().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }

    public void deleteClick(View view){

        service= serviceToDelete.getSelectedItem().toString();

        ProviderService providerService = mydatabase.findProviderService(service);
        ProviderAccount provider = mydatabase.findUsernameProviderAccount(providerService.getProviderName());

        Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

        // Passe le username à la prochaine activité
        intent.putExtra("ACCOUNTUSERNAME", provider.getUsername());

        mydatabase.deleteProviderService(service);
        Context context = getApplicationContext();
        Toast.makeText(context, "Service supprimé",
                Toast.LENGTH_SHORT).show();

        startActivityForResult (intent,0);


    }

}
