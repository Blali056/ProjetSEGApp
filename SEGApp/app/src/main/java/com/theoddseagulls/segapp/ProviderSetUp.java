package com.theoddseagulls.segapp;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class ProviderSetUp extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

    private EditText phone;
    private EditText streetNumber;
    private EditText streetName;

    private EditText pc1;
    private EditText pc2;
    private EditText pc3;
    private EditText pc4;
    private EditText pc5;
    private EditText pc6;

    private Spinner city;
    private Spinner province;
    private EditText company;
    private RadioButton yesLicence;
    private RadioButton noLicence;

    private ArrayList<String> province_options;
    private ArrayList<String> city_options;

    private static DB_handler myDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_set_up);

        phone = findViewById(R.id.phone2);
        streetNumber = findViewById(R.id.streetNumber2);
        streetName = findViewById(R.id.streetName2);

        pc1 = findViewById(R.id.pc1);
        pc2 = findViewById(R.id.pc2);
        pc3 = findViewById(R.id.pc3);
        pc4 = findViewById(R.id.pc4);
        pc5 = findViewById(R.id.pc5);
        pc6 = findViewById(R.id.pc6);

        pc1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1), new InputFilter.AllCaps()});
        pc3.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1), new InputFilter.AllCaps()});
        pc5.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1), new InputFilter.AllCaps()});

        company = findViewById(R.id.company2);

        yesLicence = findViewById(R.id.yesLicence);
        noLicence = findViewById(R.id.noLicence);

        myDataBase = new DB_handler(this);

        province = findViewById(R.id.province2);
        city = findViewById(R.id.city2);

        province_options=new ArrayList<String>();
        city_options=new ArrayList<String>();

        province_options.add("Choisir une province");
        province_options.add("ON");
        province_options.add("QC");

        city_options.add("Ville");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,city_options);
        city.setAdapter(cityAdapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,province_options);
        province.setAdapter(stateAdapter);

        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                resetCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    public void resetCity() {

        city_options.removeAll(city_options);
        if( (province.getSelectedItem().toString()).equals("ON")) {
            city_options.add("Choisir une ville");
            city_options.add("Ottawa");
            city_options.add("Toronto");

        }
        else if((province.getSelectedItem().toString()).equals("QC")) {
            city_options.add("Choisir une ville");
            city_options.add("Québec");
            city_options.add("Montréal");
            city_options.add("Gatineau");

        } else{
            city_options.add("Aucune");
        }

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,city_options);
        city.setAdapter(cityAdapter);

    }

    public void infoClick ( View view){

        if( phone.getText().length() == 0 ){
            phone.setError("Entrez un numéro de téléphone" );
        }

        if( phone.getText().length() > 0 && phone.getText().length() < 10 ){
            phone.setError("Numéro de téléphone invalide" );
        }

        if( streetNumber.getText().length() == 0){
            streetNumber.setError("Entrez un numéro");
        }

        if( streetName.getText().length() == 0){
            streetName.setError("Entrez un nom de rue");
        }

        if( pc1.getText().length() == 0){
            pc1.setError("Entrez une lettre");
        }

        if( pc2.getText().length() == 0){
            pc2.setError("Entrez un chiffre");
        }

        if( pc3.getText().length() == 0){
            pc3.setError("Entrez une lettre");
        }

        if( pc4.getText().length() == 0){
            pc4.setError("Entrez un chiffre");
        }

        if( pc5.getText().length() == 0){
            pc5.setError("Entrez une lettre");
        }

        if( pc6.getText().length() == 0){
            pc6.setError("Entrez un chiffre");
        }

        if( (province.getSelectedItem().toString()).equals("Choisir une province")){
            ((TextView)province.getChildAt(0)).setError("Choisissez une province");
        }

        if( (city.getSelectedItem().toString()).equals("Choisir une ville") || (city.getSelectedItem().toString()).equals("Aucune") ){
            ((TextView)city.getChildAt(0)).setError("Choisissez une ville");
        }

        if( company.getText().length() == 0 /*&& ((company.getText()).charAt(0)).equals(' ')*/){
            company.setError("Entrez une compagnie");
        }

        else if ( !(province.getSelectedItem().toString()).equals("Choisir une province") && !(city.getSelectedItem().toString()).equals("Choisir une ville") && ! (city.getSelectedItem().toString()).equals("Aucune") && phone.getText().length() == 10){

            Intent intent = new Intent(getApplicationContext(), ProviderAvailabilitiesSetUp.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
            intent.putExtra("ACCOUNTUSERNAME", accountUsername);

            //  Modifie l'adresse du provider
            String adNumber = streetNumber.getText().toString();
            String adName = streetName.getText().toString();
            String adCode = pc1.getText().toString() + pc2.getText().toString() + pc3.getText().toString() + " " + pc4.getText().toString() + pc5.getText().toString() + pc6.getText().toString();
            String adCity = city.getSelectedItem().toString();
            String adProvince = province.getSelectedItem().toString();

            ProviderAccount provider = myDataBase.findUsernameProviderAccount( getIntent().getStringExtra("ACCOUNTUSERNAME"));
            String address = adNumber + " " + adName + ", " + adCity + " (" + adProvince + ") " + adCode ;
            myDataBase.updateAddress(provider.getEmail(), address);
            provider.setAddress(address);

            // Modifie le phone du provider
            String providerPhone = phone.getText().toString();
            String phoneNumber = providerPhone.substring(0,3) + " " + providerPhone.substring(3,6) + "-" + providerPhone.substring(6,10);
            myDataBase.updatePhone(provider.getEmail(), phoneNumber);

            // Modifie la company du provider
            String companyName = company.getText().toString();
            myDataBase.updateCompany(provider.getEmail(), companyName);

            // Modifie la licence du provider
            String licence = null;

            if (yesLicence.isChecked()) {
                licence = "Oui";
            } else if(noLicence.isChecked()){
                licence = "Non";
            }
            myDataBase.updateLicence(provider.getEmail(), licence);

            startActivityForResult(intent, 0);
        }



    }
}
