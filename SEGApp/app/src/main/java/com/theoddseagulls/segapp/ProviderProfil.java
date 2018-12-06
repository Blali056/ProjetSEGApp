package com.theoddseagulls.segapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProviderProfil extends AppCompatActivity {

    private TextView email;
    private TextView address;
    private TextView phone;
    private TextView company;
    private TextView licence;
    private TextView licenceTitle;
    private static DB_handler myDataBase;
    private String providerUsername;
    private ProviderAccount provider;

    private ListView availabilities;

    private String samedi;
    private String dimanche;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_profil);

        email = (TextView) findViewById(R.id.providerEmail);
        address = (TextView) findViewById(R.id.providerAddress);
        phone = (TextView) findViewById(R.id.providerPhone);
        company = (TextView) findViewById(R.id.providerCompany);
        licence = (TextView) findViewById(R.id.providerLicense);
        licenceTitle = (TextView) findViewById(R.id.license);

        myDataBase = new DB_handler(this);

        providerUsername = getIntent().getStringExtra("ACCOUNTUSERNAME");
        provider = myDataBase.findUsernameProviderAccount(providerUsername);

        email.setText(provider.getEmail());
        address.setText(provider.getAddress());
        phone.setText(provider.getPhone());
        company.setText(provider.getCompany());
        licence.setText(provider.getLicence());

        if(provider.getLicence() == null){
           licence.setAlpha(0.0f);
           licenceTitle.setAlpha(0.0f);
        }


        ListView services = (ListView) findViewById(R.id.servicesList);
        ArrayList<String> serviceList = new ArrayList<>();
        Cursor providerservice = myDataBase.getProviderListContents();
        if(providerservice.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(providerservice.moveToNext()) {
                if(providerservice.getString(1).equals(providerUsername)){
                    serviceList.add(providerservice.getString(2));
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,serviceList);
                    services.setAdapter(listAdapter);
                }
            }
        }

        // Availabilities

        availabilities = (ListView) findViewById(R.id.availabilities);
        ArrayList<String> availabilitiesList = new ArrayList<>();

        samedi = myDataBase.findSamedi(providerUsername);
        dimanche =  myDataBase.findDimanche(providerUsername);
        lundi =  myDataBase.findLundi(providerUsername);
        mardi = myDataBase.findMardi(providerUsername);
        mercredi =  myDataBase.findMercredi(providerUsername);
        jeudi =  myDataBase.findJeudi(providerUsername);
        vendredi = myDataBase.findVendredi(providerUsername);


        if( samedi.indexOf("DE") <0 && samedi.indexOf("À") <0) {
            availabilitiesList.add(samedi);
        }

        if(dimanche.indexOf("DE") <0 && dimanche.indexOf("À") <0) {
            availabilitiesList.add(dimanche);
        }

        if(lundi.indexOf("DE") <0 && lundi.indexOf("À") <0) {
            availabilitiesList.add(lundi);
        }

        if(mardi.indexOf("DE") <0 && mardi.indexOf("À") <0) {
            availabilitiesList.add(mardi);
        }

        if(mercredi.indexOf("DE") <0 && mercredi.indexOf("À") <0) {
            availabilitiesList.add(mercredi);
        }

        if(jeudi.indexOf("DE") <0 && jeudi.indexOf("À") <0) {
            availabilitiesList.add(jeudi);
        }

        if(vendredi.indexOf("DE") <0 && vendredi.indexOf("À") <0) {
            availabilitiesList.add(vendredi);
        }


        ListAdapter listAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,availabilitiesList);
        availabilities.setAdapter(listAdapter1);


        services.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });



        availabilities.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });




    }

    public void editProfilClick(View view){

        Intent intent = new Intent(getApplicationContext(), EditProviderProfil.class);

        // Passe l'email à la prochaine activité
        String email = myDataBase.findUsernameAccount(providerUsername).getEmail();
        intent.putExtra("EMAIL", email);

        startActivityForResult(intent, 0);

    }

    public void addServiceClick (View view){

        Intent intent = new Intent(getApplicationContext(), AddProviderService.class);

        intent.putExtra("EMAIL", provider.getEmail());

        startActivityForResult(intent, 0);

    }

    public void deleteServiceClick (View view){

        startActivity( new Intent(getApplicationContext(), DeleteProviderService.class));

    }

    public void availabilitiesClick (View view){

        Intent intent = new Intent(getApplicationContext(), Availibilities.class);

        intent.putExtra("USERNAME", provider.getUsername());

        intent.putExtra("SAMEDI",samedi );
        intent.putExtra("DIMANCHE",dimanche );
        intent.putExtra("LUNDI",lundi);
        intent.putExtra("MARDI",mardi);
        intent.putExtra("MERCREDI",mercredi );
        intent.putExtra("JEUDI",jeudi);
        intent.putExtra("VENDREDI",vendredi);

        startActivityForResult(intent, 0);

    }

    public void decoClick( View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }

}
