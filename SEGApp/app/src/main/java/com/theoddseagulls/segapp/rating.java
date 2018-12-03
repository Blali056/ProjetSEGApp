package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class rating extends AppCompatActivity {
    private EditText rate_it_editText;
    private EditText comment_it_editText;
    private static DB_handler mydatabase;
    private String rate;
    private String provider;
    private String comment ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        rate_it_editText = (EditText) findViewById(R.id.rate_it_editText);
        comment_it_editText =(EditText) findViewById(R.id.comment_it_editText2);
        mydatabase = new DB_handler(this);


    }

    public void rateItClick(View view) {

        if (rate_it_editText.getText().length() == 0) {
            rate_it_editText.setError("Entrez une évaluation");
        } else if (Double.parseDouble(rate_it_editText.getText().toString()) < 1 || Double.parseDouble(rate_it_editText.getText().toString()) > 5) {
            rate_it_editText.setError("Une évaluation est entre 0 et 5");

        } else if(comment_it_editText.getText().length() == 0){
            comment_it_editText.setError("Entrez un Commentaire");

        } else {
            String providerEmail = getIntent().getStringExtra("EMAIL");
            ProviderAccount providerAccount = mydatabase.findProviderAccountByEmail(providerEmail);
            int rate = Integer.parseInt(rate_it_editText.getText().toString());
            providerAccount.setRate(rate);
            mydatabase.addProvider_rate(providerAccount);
            Context context = getApplicationContext();
            Toast.makeText(context, "Évaluation  ajouté", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), FournisseurProfil.class);

            intent.putExtra("USERNAME", providerAccount.getUsername());

            startActivityForResult(intent, 0);





        }


        // Va a la liste de services

    }
}





