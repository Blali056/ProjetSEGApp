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

public class SearchByService extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private Spinner serviceName;
    private static  DB_handler mydatabase;
    private ListView providersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_service);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

        //Spinner
        mydatabase = new DB_handler(this);
        serviceName = findViewById(R.id.service);

        ArrayList<String> service_options =new ArrayList<>();

        Cursor service = mydatabase.getListContents();

        if(service.getCount() != 0) {       //S'il y a des services dans la base de donnee
            while (service.moveToNext()) {
                service_options.add(service.getString(1) + " - " + service.getString(2) + "$/heure");
            }
        } else {
            service_options.add("Aucun service");
        }

        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item,service_options);
        serviceName.setAdapter(Adapter);

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

        if(id == R.id.listOfServices){
            startActivity(new Intent(getApplicationContext(),ListOfService.class ));
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

    public void searchClick(View view){

        providersList = (ListView) findViewById(R.id.providerList);
        ArrayList<String> providerList = new ArrayList<>();
        Cursor providerservice = mydatabase.getProviderListContents();
        if(providerservice.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(providerservice.moveToNext()) {
                if(serviceName.getSelectedItem().toString().equals(providerservice.getString(2))){
                    ProviderAccount p = mydatabase.findUsernameProviderAccount(providerservice.getString(1));
                    providerList.add(p.getName() + " " + p.getLastName());
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                    providersList.setAdapter(listAdapter);
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

                startActivityForResult(intent, 0);
            }
        });

    }

}
