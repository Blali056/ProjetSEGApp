package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddService extends AppCompatActivity {
    private EditText serviceName;
    private EditText tauxHoraire;
    private Service service;
    private static  DB_handler mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sevice);

        serviceName = (EditText) findViewById(R.id.serviceToDelete);
        tauxHoraire = (EditText) findViewById(R.id.tauxHoraireAdded);
        service= new Service();
        mydatabase = new DB_handler(this);
    }


    public boolean serviceExist (){
        boolean exist = true;
        Service serviceInList = mydatabase.findService(serviceName.getText().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }

    public void addClick(View view){
        if(( serviceName.getText().length() == 0 )||(Character.isWhitespace(serviceName.getText().charAt(0)))){
            serviceName.setError("Entrez un service" );
        }
        if(( tauxHoraire.getText().length() == 0)|| (Character.isWhitespace( tauxHoraire.getText().charAt(0))) ){
            tauxHoraire.setError("Entrez un taux horaire" );
        }
        else if(serviceExist() == true ){       // Si le service existe deja
            serviceName.setError("Service existant");
        }
        else{
            service.setService(serviceName.getText().toString());
            service.setTauxHoraire(Double.parseDouble(tauxHoraire.getText().toString()));
            mydatabase.addService(service);
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouté",
                    Toast.LENGTH_SHORT).show();

            // Va a la liste de services
            Intent intent = new Intent(getApplicationContext(), servicelist.class);
            startActivityForResult (intent,0);
        }

    }
    public void signOutClick(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult (intent,0);
    }




}
