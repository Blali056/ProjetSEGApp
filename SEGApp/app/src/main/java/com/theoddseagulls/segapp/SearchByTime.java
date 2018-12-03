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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchByTime extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Spinner dayOfTheWeek;
    private static  DB_handler mydatabase;
    private ListView providersList;
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
        setContentView(R.layout.activity_search_by_time);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);
        dayOfTheWeek= findViewById(R.id.days);
        ArrayList<String> days =new ArrayList<>();
        days.add("Lundi");
        days.add("Mardi");
        days.add("Mercredi");
        days.add("Jeudi");
        days.add("Vendredi");
        days.add("Samedi");
        days.add("Dimanche");
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,days);
        dayOfTheWeek.setAdapter(Adapter);




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.profil){
            startActivity(new Intent(getApplicationContext(),UserProfil.class ));
        }

        if(id == R.id.byService){
            startActivity(new Intent(getApplicationContext(),SearchByService.class ));
        }

        if(id == R.id.byProvider){
            startActivity(new Intent(getApplicationContext(),SearchByProvider.class ));
        }

        if(id == R.id.byRating){
            startActivity(new Intent(getApplicationContext(),SearchByRating.class ));
        }

        if(id == R.id.byTime){
            startActivity(new Intent(getApplicationContext(),SearchByTime.class ));
        }



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void OnClickSearch(View view) {
        providersList = (ListView) findViewById(R.id.providerListJour);
        ArrayList<String> providerList = new ArrayList<>();
        Cursor providerAvailabilities = mydatabase.getListAvailabilities();
        if (providerAvailabilities.getCount() != 0) {
            while (providerAvailabilities.moveToNext()) {
                samedi = mydatabase.findSamedi(providerAvailabilities.getString(1));
                dimanche =  mydatabase.findDimanche(providerAvailabilities.getString(1));
                lundi =  mydatabase.findLundi(providerAvailabilities.getString(1));
                mardi = mydatabase.findMardi(providerAvailabilities.getString(1));
                mercredi =  mydatabase.findMercredi(providerAvailabilities.getString(1));
                jeudi =  mydatabase.findJeudi(providerAvailabilities.getString(1));
                vendredi = mydatabase.findVendredi(providerAvailabilities.getString(1));
                if (dayOfTheWeek.getSelectedItem().toString().equals("Samedi")) {
                    if(samedi.indexOf("DE") <0 && samedi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }

                if (dayOfTheWeek.getSelectedItem().toString().equals("Dimanche")) {
                    if(dimanche.indexOf("DE") <0 && dimanche.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }
                if (dayOfTheWeek.getSelectedItem().toString().equals("Lundi")) {
                    if(lundi.indexOf("DE") <0 && lundi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }
                if (dayOfTheWeek.getSelectedItem().toString().equals("Mardi")) {
                    if(mardi.indexOf("DE") <0 && mardi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }
                if (dayOfTheWeek.getSelectedItem().toString().equals("Mercredi")) {
                    if(mercredi.indexOf("DE") <0 && mercredi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }
                if (dayOfTheWeek.getSelectedItem().toString().equals("Jeudi")) {
                    if(jeudi.indexOf("DE") <0 && jeudi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }
                if (dayOfTheWeek.getSelectedItem().toString().equals("Vendredi")) {
                    if(vendredi.indexOf("DE") <0 && vendredi.indexOf("À") <0){
                        ProviderAccount p = mydatabase.findUsernameProviderAccount(providerAvailabilities.getString(1));
                        providerList.add(p.getName() + " " + p.getLastName());
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providersList.setAdapter(listAdapter);

                    }

                }






            }
        }
        providersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FournisseurProfil.class);

                String fullName = (String) providersList.getItemAtPosition(position);

                String[] parts = fullName.split(" ");
                String name = parts[0];

                ProviderAccount providerSelected = mydatabase.findProviderAccountByName(name);
                intent.putExtra("USERNAME", providerSelected.getUsername());

                String userUsername = getIntent().getStringExtra("UUSERNAME");
                intent.putExtra("USERNAMEUSER", userUsername );

                startActivityForResult(intent, 0);
            }
        });

    }
}
