package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModifySErvice extends AppCompatActivity {

    private EditText serviceName;
    private EditText newTauxHoraire;
    private String service;
    private double tauxHoraire;
    private static  DB_handler mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_service);

        serviceName = (EditText) findViewById(R.id.servicemodifytv);
        newTauxHoraire = (EditText) findViewById(R.id.newrateEt);
        mydatabase = new DB_handler(this);

    }

    public boolean serviceExist (){
        boolean exist = true;
        Service serviceInList = mydatabase.findService(serviceName.getText().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }

    public void modifyClick(View view){
        if( serviceName.getText().length() == 0 ){       // Si aucun service n'est entré
            serviceName.setError("Entrez un service" );
        }
        if( newTauxHoraire.getText().length() == 0 ){       // Si aucun taux horaire n'est entré
            newTauxHoraire.setError("Entrez un taux horaire" );
        }
        else if(serviceExist() == false ){       // Si le service n'existe pas
            serviceName.setError("Service inexistant");
        }
        else{
            service=serviceName.getText().toString();
            tauxHoraire=Double.parseDouble(newTauxHoraire.getText().toString());
            mydatabase.modifyService(service,tauxHoraire);
            Context context = getApplicationContext();
            Toast.makeText(context, "Service modifié",
                    Toast.LENGTH_SHORT).show();

            // Va a la liste de services
            Intent intent = new Intent(getApplicationContext(), servicelist.class);
            startActivityForResult (intent,0);
        }

    }





}
