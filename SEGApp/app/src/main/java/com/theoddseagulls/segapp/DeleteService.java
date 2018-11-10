package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteService extends AppCompatActivity {
    private EditText serviceToDelete;
    private static  DB_handler mydatabase;
    private String service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);

        serviceToDelete = (EditText) findViewById(R.id.servicedeleteET);
        mydatabase = new DB_handler(this);

    }

    public boolean serviceExist (){
        boolean exist = true;
        Service serviceInList = mydatabase.findService(serviceToDelete.getText().toString());
        if(serviceInList == null)
            exist = false;
        return exist;
    }

    public void deleteClick(View view){
        if( serviceToDelete.getText().length() == 0 ){       // Si aucun service n'est entré
            serviceToDelete.setError("Entrez un service" );
        }
        else if(serviceExist() == false ){       // Si l'email et le mot de passe sont invalides
            serviceToDelete.setError("Service Inexistant");
        }
        else{
            service= serviceToDelete.getText().toString();
            mydatabase.deleteService(service);
            Context context = getApplicationContext();
            Toast.makeText(context, "Service supprimé",
                    Toast.LENGTH_SHORT).show();

            // Va a la liste de services
            Intent intent = new Intent(getApplicationContext(), servicelist.class);
            startActivityForResult (intent,0);
        }

    }

}
