package com.theoddseagulls.segapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeUsername;
    private TextView welcomeType;
    private TextView welcome;
    String type=getIntent().getStringExtra("TYPE");


    private Typeface typeFace;       //Pour changer le font

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeUsername = (TextView) findViewById(R.id.welcomeUsername);
        welcomeType = (TextView) findViewById(R.id.welcomeType);
        welcome = (TextView) findViewById(R.id.phrase);


        // change le font de welcomeUsername, welcomeType et la phrase
        typeFace = Typeface.createFromAsset(getAssets(), "fonts/CabinRegular.ttf");
        welcomeUsername.setTypeface(typeFace);
        welcomeType.setTypeface(typeFace);
        welcome.setTypeface(typeFace);

        // Recoit le username de l'activité precendente
        welcomeUsername.setText("Bienvenue " + getIntent().getStringExtra("USERNAME"));

        // Recoit le type de l'activité precendente
        welcomeType.setText(type);

    }

    public void continuebtnClick(View view) {

        // Ouvre l'activite
            if(type=="Administrateur") {
                Intent intent = new Intent(getApplicationContext(), configuration.class);
                startActivityForResult(intent, 0);
            }

    }
}
