package com.theoddseagulls.segapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private DB_handler mydatabase;
    private UserAccount user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        user = new UserAccount(email.getText().toString(), password.getText().toString());
        mydatabase = new DB_handler(this);

    }


    public boolean validateEmail(){

        boolean isValid = true;

        String validEmail = mydatabase.findEmail(email.getText().toString());
        String enteredEmail = email.getText().toString();

        if( enteredEmail.equals(validEmail) == false){          // S'il n'existe pas un compte avec cet email
            isValid = false;
        }

        return isValid;
    }


    public boolean validatePassword(){

        boolean isValid = true;

        String validPassword = (mydatabase.findUser(email.getText().toString())).getPassword();
        String enteredPassword = password.getText().toString();

        if( enteredPassword.equals(validPassword) == false){        // Si le password est faux
            isValid = false;
        }

        return isValid;
    }


    // onClick du bouton SE CONNECTER
    public void loginClick(View view) {

        if( email.getText().length() == 0 ){       // Si aucun email n'est entré
            email.setError("Entrez un email" );
        }

        if( password.getText().length() == 0){         // Si aucun password n'est entré
            password.setError("Entrez un mot de passe");
        }

        else if(validateEmail() == false && validatePassword() == false){       // Si l'email et le mot de passe sont invalides
            email.setError("Compte inexistant");
            password.setError("Mot de passe incorrect");
        }

        else if(validateEmail() == false){                // Si l'email est invalide
            email.setError("Compte inexistant");
        }

        else if(validatePassword() == false){         // Si le password est invalide
            password.setError("Mot de passe incorrect");
        }

        else if (validateEmail() == true && validatePassword() == true) {
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivityForResult (intent,0);
        }


    }

}
