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

import java.security.Provider;
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

   /* private TextView samedi;
    private TextView dimanche;
    private TextView lundi;
    private TextView mardi;
    private TextView mercredi;
    private TextView jeudi;
    private TextView vendredi;*/

    private ListView availabilities;



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


        ListView services = (ListView) findViewById(R.id.providerServicesList);
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

        String samedi = provider.getSamedi();
        String dimanche = provider.getDimanche();
        String lundi = provider.getLundi();
        String mardi = provider.getMardi();
        String mercredi = provider.getMercredi();
        String jeudi = provider.getJeudi();
        String vendredi = provider.getVendredi();

        if(! samedi.equals("Samedi : DE - À")) {
            availabilitiesList.add(samedi);
        }

        if(! dimanche.equals("Dimanche : DE - À")) {
            availabilitiesList.add(dimanche);
        }

        if(! lundi.equals("Lundi : DE - À")) {
            availabilitiesList.add(lundi);
        }

        if(! mardi.equals("Mardi : DE - À")) {
            availabilitiesList.add(mardi);
        }

        if(! mercredi.equals("Mercredi : DE - À")) {
            availabilitiesList.add(mercredi);
        }

        if(! jeudi.equals("Jeudi : DE - À")) {
            availabilitiesList.add(jeudi);
        }

        if(! vendredi.equals("Vendredi : DE - À")) {
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

        startActivityForResult(intent, 0);

    }

}
