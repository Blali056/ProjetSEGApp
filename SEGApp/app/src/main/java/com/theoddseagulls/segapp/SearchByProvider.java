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

import java.util.ArrayList;

public class SearchByProvider extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView providers;
    private static  DB_handler mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_provider);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

        providers = (ListView) findViewById(R.id.providerList2);
        mydatabase = new DB_handler(this);

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
    public void SearchByProviderClick(View view){
        ArrayList<String> providerList = new ArrayList<>();
        Cursor provider = mydatabase.getProviderListContents();
        ArrayList<String> providerUserName = new ArrayList<>();



        if(provider.getCount()!=0){

            while(provider.moveToNext()) {

                ProviderAccount p = mydatabase.findUsernameProviderAccount(provider.getString(1));
                if (providerUserName.indexOf(p.getUsername()) == -1) {
                    providerUserName.add(p.getUsername());
                    providerList.add(p.getUsername());
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, providerList);
                    providers.setAdapter(listAdapter);

                }
            }


            }
        providers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FournisseurProfil.class);

                String userName = (String) providers.getItemAtPosition(position);



                ProviderAccount providerSelected = mydatabase.findUsernameProviderAccount(userName);

                intent.putExtra("USERNAME", providerSelected.getUsername());

                startActivityForResult(intent, 0);
            }
        });


        }



}


