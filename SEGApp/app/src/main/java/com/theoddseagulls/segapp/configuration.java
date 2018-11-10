package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Button addButton = (Button) findViewById(R.id.addServicebtn);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), addSevice.class));
            }
        });

        Button deleteButton = (Button) findViewById(R.id.removeservicebtn);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), DeleteService.class));
            }
        });

        Button serviceListButton = (Button) findViewById(R.id.servicebtn);
        serviceListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), servicelist.class));
            }
        });

        Button modifyButton = (Button) findViewById(R.id.modifyServicebtn);
        modifyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ModifySErvice.class));
            }
        });

    }

}
