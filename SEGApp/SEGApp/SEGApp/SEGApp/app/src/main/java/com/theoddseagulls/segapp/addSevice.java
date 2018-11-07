package com.theoddseagulls.segapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addSevice extends AppCompatActivity {
    private EditText serviceName;
    private EditText rate;
    private static  DB_handler mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sevice);
        serviceName = (EditText) findViewById(R.id.serviceaddEt);
        rate = (EditText) findViewById(R.id.rateAddEt);
        mydatabase = new DB_handler(this);


    }
    public boolean serviceExist (){
        boolean isValid = true;
        Service serviceInList = mydatabase.findService(serviceName.getText().toString());
        if(serviceInList == null)
            return isValid;
        return !isValid;

    }
    public void addClick(View view){
        if( serviceName.getText().length() == 0 ){       // Si aucun email n'est entré
            serviceName.setError("Entrez un service" );
        }
        if( rate.getText().length() == 0 ){       // Si aucun email n'est entré
            rate.setError("Entrez un rate" );
        }
        else if(serviceExist() == false ){       // Si l'email et le mot de passe sont invalides
            serviceName.setError("Service Existant");
        }
        else{
            AdminAccount.getTheaccount().addService(serviceName.getText().toString(),Double.parseDouble(rate.getText().toString()));
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouter",
                    Toast.LENGTH_LONG).show();
        }

    }




}
