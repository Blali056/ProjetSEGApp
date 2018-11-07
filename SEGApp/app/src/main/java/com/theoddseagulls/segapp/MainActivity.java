package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onClick du bouton CONNEXION
    public void signInBtnClick(View view) {

        // Ouvre l'activite pour se connceter
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivityForResult (intent,0);
    }


    // onClick du bouton INSCRIPTION
    public void signUpBtnClick(View view) {

        // Ouvre l'activite pour s'inscrire
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivityForResult (intent,0);
    }
}
