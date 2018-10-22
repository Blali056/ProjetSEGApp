package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView info;
    private Button login;
    private Button register;
    private DB_handler mydatabase= new DB_handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onButtonClick(View v){
        if(v.getId()==R.id.btnLogin) {
            email = (EditText) findViewById(R.id.etEmail);
            password = (EditText) findViewById(R.id.etPassword);
             info = (TextView) findViewById(R.id.tvInfo);
            User user = new User(email.getText().toString(), password.getText().toString());

            String pass = (mydatabase.findUser(user)).getPassword();
            String e = (mydatabase.findUser(user)).getEmail();
            if (password.equals(pass) && email.equals(e)) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Username", user.getPassword());
                startActivity(intent);
            } else {
                Toast pass1 = Toast.makeText(MainActivity.this, "You are not registred", Toast.LENGTH_SHORT);
                pass1.show();
            }
        }
        else if(v.getId()==R.id.btnRegister){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
    public void about(View view) {
        Intent aboutIntent = new Intent(this, About.class);
        startActivity(aboutIntent);
    }


}
