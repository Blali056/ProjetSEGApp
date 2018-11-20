package com.theoddseagulls.segapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeUsername;
    private TextView welcomeType;
    private TextView welcome;
    private String type;
    private ProviderAccount providerAccount;
    private static DB_handler myDataBase;


    private Typeface typeFace;       //Pour changer le font

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeUsername = (TextView) findViewById(R.id.welcomeUsername);
        welcomeType = (TextView) findViewById(R.id.welcomeType);
        welcome = (TextView) findViewById(R.id.phrase);
        myDataBase = new DB_handler(this);

        type = getIntent().getStringExtra("TYPE");

        // change le font de welcomeUsername, welcomeType et la phrase
        typeFace = Typeface.createFromAsset(getAssets(), "fonts/CabinRegular.ttf");
        welcomeUsername.setTypeface(typeFace);
        welcomeType.setTypeface(typeFace);
        welcome.setTypeface(typeFace);

        // Recoit le username de l'activité precendente
        welcomeUsername.setText("Bienvenue " + getIntent().getStringExtra("USERNAME"));

        // Recoit le type de l'activité precendente
        welcomeType.setText(getIntent().getStringExtra("TYPE"));

        Button btn = (Button) findViewById(R.id.continuebtn);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               if(type.equals("Administrateur")){
                   startActivity(new Intent(getApplicationContext(),configuration.class ));
               }

                if(type.equals("Fournisseur")){

                    providerAccount = myDataBase.findUsernameProviderAccount(getIntent().getStringExtra("USERNAME"));

                    if((providerAccount.getAddress()) == null || (providerAccount.getPhone()) == null || (providerAccount.getCompany()) == null){           // Nouveau providerAccount sans info
                        Intent intent1 = new Intent(getApplicationContext(), ProviderSetUp.class);

                        // Passe le username à la prochaine activité
                        String accountUsername = getIntent().getStringExtra("USERNAME");
                        intent1.putExtra("ACCOUNTUSERNAME", accountUsername);

                        startActivityForResult(intent1, 0);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

                        // Passe le username à la prochaine activité
                        String accountUsername = getIntent().getStringExtra("USERNAME");
                        intent.putExtra("ACCOUNTUSERNAME", accountUsername);

                        startActivityForResult(intent, 0);
                    }

                }
            }
        });


    }


}