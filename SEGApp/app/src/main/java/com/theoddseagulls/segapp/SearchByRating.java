package com.theoddseagulls.segapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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

import java.util.ArrayList;

public class SearchByRating extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView providers;
    private static  DB_handler mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rating);


        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);
        providers = (ListView) findViewById(R.id.providerListRate);
        mydatabase = new DB_handler(this);








    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.profil){
            Intent intent = new Intent(getApplicationContext(), UserProfil.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);
        }

        if(id == R.id.byService){
            Intent intent = new Intent(getApplicationContext(), SearchByService.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);
        }

        if(id == R.id.byProvider){
            Intent intent = new Intent(getApplicationContext(), SearchByProvider.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);
        }

        if(id == R.id.byRating){
            Intent intent = new Intent(getApplicationContext(), SearchByRating.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);
        }

        if(id == R.id.byTime){
            Intent intent = new Intent(getApplicationContext(), SearchByTime.class);

            // Passe le username à la prochaine activité
            String accountUsername = getIntent().getStringExtra("USERNAMEUSER");
            intent.putExtra("USERNAMEUSER", accountUsername);

            startActivityForResult(intent, 0);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showProviderClick(View view){

        ArrayList<String> providerList = new ArrayList<>();
        Cursor provider = mydatabase.getProviderListContents();
        double rate ;
        ArrayList<String> providerUserName = new ArrayList<>();



        if(provider.getCount()!=0){
            while(provider.moveToNext()){

                ProviderAccount p = mydatabase.findUsernameProviderAccount(provider.getString(1));
                if(providerUserName.indexOf(p.getUsername())==-1){
                    providerUserName.add(p.getUsername());
                    if(mydatabase.find_provider_rate(p.getUsername())!=null){
                        rate=Double.parseDouble(mydatabase.find_provider_rate(p.getUsername()));
                        providerList.add(p.getName() + " " + p.getLastName() + " - Évaluation: "+ rate);

                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providers.setAdapter(listAdapter);

                    }
                    else{
                        providerList.add(p.getName() + " " + p.getLastName() +  " - Évaluation: Aucune ");
                        ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                        providers.setAdapter(listAdapter);
                    }

                }


                }


                }


        providers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FournisseurProfil.class);

                String fullName = (String) providers.getItemAtPosition(position);

                String[] parts = fullName.split(" ");
                String userName = parts[0];

                ProviderAccount providerSelected = mydatabase.findUsernameProviderAccount(userName);

                intent.putExtra("USERNAME", providerSelected.getUsername());

                String userUsername = getIntent().getStringExtra("USERNAMEUSER");
                intent.putExtra("USERNAMEUSER", userUsername );

                startActivityForResult(intent, 0);
            }
        });




    }
    }


