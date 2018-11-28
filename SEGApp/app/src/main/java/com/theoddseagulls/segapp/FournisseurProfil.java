package com.theoddseagulls.segapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FournisseurProfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private static DB_handler mydatabase;

    private TextView email;
    private TextView address;
    private TextView phone;
    private TextView company;
    private TextView licence;
    private TextView licenceTitle;
    private TextView title;
    private TextView rate;
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
        setContentView(R.layout.activity_fournisseur_profil);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

        //
        email = (TextView) findViewById(R.id.providerEmail);
        address = (TextView) findViewById(R.id.providerAddress);
        phone = (TextView) findViewById(R.id.providerPhone);
        company = (TextView) findViewById(R.id.providerCompany);
        licence = (TextView) findViewById(R.id.providerLicense);
        licenceTitle = (TextView) findViewById(R.id.license);
        title = (TextView) findViewById(R.id.title1);
        rate= (TextView) findViewById(R.id.ProviderRating);

        mydatabase = new DB_handler(this);

        String providerUsername = getIntent().getStringExtra("USERNAME");
        ProviderAccount provider = mydatabase.findUsernameProviderAccount(providerUsername);

        email.setText(provider.getEmail());
        address.setText(provider.getAddress());
        phone.setText(provider.getPhone());
        company.setText(provider.getCompany());
        licence.setText(provider.getLicence());
        title.setText(provider.getName() + " " + provider.getLastName());

        Cursor providerRate = mydatabase.getProviderRateContents();
        if(providerRate.getCount() != 0) {       //S'il y a des rates dans la base de donnee
            rate.setText((mydatabase.find_provider_rate(provider.getEmail())));
        }

        if(provider.getLicence() == null){
            licence.setAlpha(0.0f);
            licenceTitle.setAlpha(0.0f);
        }


        ListView services = (ListView) findViewById(R.id.servicesList);
        ArrayList<String> serviceList = new ArrayList<>();
        Cursor providerservice = mydatabase.getProviderListContents();
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

        samedi = mydatabase.findSamedi(providerUsername);
        dimanche =  mydatabase.findDimanche(providerUsername);
        lundi =  mydatabase.findLundi(providerUsername);
        mardi = mydatabase.findMardi(providerUsername);
        mercredi = mydatabase.findMercredi(providerUsername);
        jeudi =  mydatabase.findJeudi(providerUsername);
        vendredi = mydatabase.findVendredi(providerUsername);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.profil) {
            startActivity(new Intent(getApplicationContext(), UserProfil.class));
        }

        if (id == R.id.byService) {
            startActivity(new Intent(getApplicationContext(), SearchByService.class));
        }

        if (id == R.id.byProvider) {
            startActivity(new Intent(getApplicationContext(), SearchByProvider.class));
        }

        if (id == R.id.byRating) {
            startActivity(new Intent(getApplicationContext(), SearchByRating.class));
        }

        if (id == R.id.byTime) {
            startActivity(new Intent(getApplicationContext(), SearchByTime.class));
        }

        if (id == R.id.listOfServices) {
            startActivity(new Intent(getApplicationContext(), ListOfService.class));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void bookNowClick (View view){
        Intent intent = new Intent(getApplicationContext(), book_now.class);

        intent.putExtra("EMAIL", email.getText());
        String providerUsername = getIntent().getStringExtra("USERNAME");

        intent.putExtra("USERNAME",providerUsername);

        startActivityForResult(intent, 0);

    }
    public void  rateClick(View view){

        Intent intent = new Intent(getApplicationContext(), rating.class);

        intent.putExtra("EMAIL", email.getText());

        startActivityForResult(intent, 0);
    }}