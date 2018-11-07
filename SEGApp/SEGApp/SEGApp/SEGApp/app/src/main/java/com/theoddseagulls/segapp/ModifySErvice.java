package com.theoddseagulls.segapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModifySErvice extends AppCompatActivity {
    private EditText serviceName;
    private EditText newrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_service);
        serviceName = (EditText) findViewById(R.id.servicemodifytv);
        newrate = (EditText) findViewById(R.id.newrateEt);
    }
    public boolean serviceExist () {
        boolean exist = true;
        Service serviceInList = AdminAccount.getTheaccount().getServicesList().get(AdminAccount.getTheaccount().getServicesList().indexOf(serviceName.getText().toString()));
        if (serviceInList == null)
            return !exist;
        return exist;
    }

    public void modifyClick(View view){
        if( serviceName.getText().length() == 0 ){       // Si aucun email n'est entré
            serviceName.setError("Entrez un service" );
        }
        if( newrate.getText().length() == 0 ){       // Si aucun email n'est entré
            newrate.setError("Entrez un rate" );
        }
        else if(serviceExist() == false ){       // Si l'email et le mot de passe sont invalides
            serviceName.setError("Service inexistant");
        }
        else{
            AdminAccount.getTheaccount().changeServiceRate(serviceName.getText().toString(),Double.parseDouble(newrate.getText().toString()));
            Context context = getApplicationContext();
            Toast.makeText(context, "Service ajouter",
                    Toast.LENGTH_LONG).show();
        }

    }





}
