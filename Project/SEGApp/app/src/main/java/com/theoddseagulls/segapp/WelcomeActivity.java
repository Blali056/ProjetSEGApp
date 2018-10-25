package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    /*private EditText toastMsg;
    private Button toastIt;
    String value = getIntent().getStringExtra("Username");
    TextView welcomeView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*Toast.makeText(getApplicationContext(), "You're logged in",
                Toast.LENGTH_SHORT).show();

        welcomeView.setText("Welcome"+value);*/
    }
}
