package com.theoddseagulls.segapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserProfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private static DB_handler mydatabase;
    private TextView none;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        none = findViewById(R.id.none);

        mydatabase = new DB_handler(this);

        String username = getIntent().getStringExtra("USERNAMEUSER");

        ListView rdv = (ListView) findViewById(R.id.rdvList);
        ArrayList<String> rdvList = new ArrayList<>();
        Cursor appointment = mydatabase.getUserAppointment();
        if(appointment.getCount() != 0){       //S'il y a des services dans la base de donnee
            while(appointment.moveToNext()) {
                if(appointment.getString(1).equals(username)){
                    rdvList.add(appointment.getString(3) + " avec " + appointment.getString(2));
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rdvList);
                    rdv.setAdapter(listAdapter);
                }
            }
        }

        if(!rdvList.isEmpty()){
            none.setVisibility(View.INVISIBLE);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

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

    public void decoClick( View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }

}
