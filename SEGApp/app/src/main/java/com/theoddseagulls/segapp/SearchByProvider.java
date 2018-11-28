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

        providers = (ListView) findViewById(R.id.providerListRate);
        ArrayList<String> providerList = new ArrayList<>();
        Cursor providerservice = mydatabase.getProviderListContents();
        if(providerservice.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(providerservice.moveToNext()) {
                providerList.add(providerservice.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,providerList);
                providers.setAdapter(listAdapter);}

        }

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

}
