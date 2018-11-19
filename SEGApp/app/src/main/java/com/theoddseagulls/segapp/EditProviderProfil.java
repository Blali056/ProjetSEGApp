package com.theoddseagulls.segapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class EditProviderProfil extends AppCompatActivity {

    private TextView email;
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
    private ProviderAccount provider;

    private String address_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_provider_profil);

        email = findViewById(R.id.providerEmail);
        phone = findViewById(R.id.phone);
        streetNumber = findViewById(R.id.streetNumber);
        streetName = findViewById(R.id.streetName);

        pc1 = findViewById(R.id.pc1);
        pc2 = findViewById(R.id.pc2);
        pc3 = findViewById(R.id.pc3);
        pc4 = findViewById(R.id.pc4);
        pc5 = findViewById(R.id.pc5);
        pc6 = findViewById(R.id.pc6);

        company = findViewById(R.id.company);

        yesLicence = findViewById(R.id.yesLicence);
        noLicence = findViewById(R.id.noLicence);

        myDataBase = new DB_handler(this);


        provider = myDataBase.findProviderAccount( getIntent().getStringExtra("EMAIL"));
        email.setText( getIntent().getStringExtra("EMAIL") );

        String phoneNum = provider.getPhone();
        phone.setText(phoneNum.substring(0,3) + phoneNum.substring(4,7) + phoneNum.substring(8,12) );
        company.setText(provider.getCompany());

        String address = provider.getAddress();

        address_city = "";

        String[] parts = address.split(" ");
        String stNumber = parts[0];
        String stName = parts[1].substring(0,(parts[1].length()) - 1);
        address_city = parts[2];
        String address_province = parts[3].substring(1,(parts[3].length()) - 1);
        String address_pc1 = parts[4].substring(0);
        String address_pc2 = parts[4].substring(1);
        String address_pc3 = parts[4].substring(2);
        String address_pc4 = parts[5].substring(0);
        String address_pc5 = parts[5].substring(1);
        String address_pc6 = parts[5].substring(2);

        streetNumber.setText(stNumber);
        streetName.setText(stName);

        pc1.setText(address_pc1);
        pc2.setText(address_pc2);
        pc3.setText(address_pc3);
        pc4.setText(address_pc4);
        pc5.setText(address_pc5);
        pc6.setText(address_pc6);

        if( provider.getLicence() == null){
            yesLicence.setChecked(false);
            noLicence.setChecked(false);
        } else if((provider.getLicence()).equals("Oui")){
            yesLicence.setChecked(true);
            noLicence.setChecked(false);
        } else if((provider.getLicence()).equals("Non")){
            yesLicence.setChecked(false);
            noLicence.setChecked(true);
        }


        province = findViewById(R.id.province);
        city = findViewById(R.id.city);

        province_options=new ArrayList<String>();
        city_options=new ArrayList<String>();

        province_options.add("Choisir une province");
        province_options.add("ON");
        province_options.add("QC");

        city_options.add("Ville");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,city_options);
        city.setAdapter(cityAdapter);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,province_options);
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

        province.setSelection(getIndex(province, address_province));



    }


    //Pour set le choix de province et city
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return -1;
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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,city_options);
        city.setAdapter(cityAdapter);
        city.setSelection(getIndex(city, address_city));

    }

    public void personalInfoClick ( View view){

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

        if( company.getText().length() == 0){
            company.setError("Entrez une compagnie");
        }

        else if ( !(province.getSelectedItem().toString()).equals("Choisir une province") && !(city.getSelectedItem().toString()).equals("Choisir une ville") && ! (city.getSelectedItem().toString()).equals("Aucune") && phone.getText().length() == 10){

            Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

            // Passe le username à la prochaine activité
            intent.putExtra("ACCOUNTUSERNAME", provider.getUsername());

            //  Modifie l'adresse du provider
            String adNumber = streetNumber.getText().toString();
            String adName = streetName.getText().toString();
            String adCode = pc1.getText().toString() + pc2.getText().toString() + pc3.getText().toString() + " " + pc4.getText().toString() + pc5.getText().toString() + pc6.getText().toString();
            String adCity = city.getSelectedItem().toString();
            String adProvince = province.getSelectedItem().toString();

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
