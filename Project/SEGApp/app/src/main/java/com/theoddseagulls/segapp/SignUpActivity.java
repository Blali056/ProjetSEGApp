package com.theoddseagulls.segapp;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private static int adminNumberAccount  = 0;
    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText username;
    private String type;
    private UserAccount user;
    private AdminAccount admin;
    private ProviderAccount provider;
    private RadioGroup typeChoices;
    private TextView askType;
    private DB_handler myDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.prenom);
        lastName = (EditText) findViewById(R.id.nom);
        email = (EditText) findViewById(R.id.e_mail);
        password = (EditText) findViewById(R.id.pass_word);
        username = (EditText) findViewById(R.id.username);
        user = new UserAccount(email.getText().toString(), password.getText().toString());
        provider = new ProviderAccount(email.getText().toString(), password.getText().toString());
        admin = new AdminAccount(email.getText().toString(), password.getText().toString());
        typeChoices = (RadioGroup) findViewById(R.id.typeOptions);
        askType = (TextView) findViewById(R.id.enterType);
        myDataBase = new DB_handler(this);
    }


    public boolean validateEmail(){

        boolean isValid = true;

        String validEmail = myDataBase.findEmail(email.getText().toString());
        String enteredEmail = email.getText().toString();

        if( enteredEmail.equals(validEmail) == true){           // S'il existe deja un compte avec cet email
            isValid = false;
        }

        return isValid;
    }


    public boolean validateUsername(){

        boolean isValid = true;

        String validUsername = myDataBase.findUsername(username.getText().toString());
        String enteredUsername = username.getText().toString();

        if( enteredUsername.equals(validUsername) == true){           // S'il existe deja un compte avec ce username
            isValid = false;
        }

        return isValid;
    }


    // onClick du radio group pour le choix de type de compte
    public void typeOptionsClick(View view){

        boolean checked = ((RadioButton) view).isChecked();     // Verifie si une option est selectionnée

        // Vérifie quelle option est selectionné
        switch(view.getId()) {
            case R.id.user:
                if (checked)
                    type = "Utilisateur";
                    user.setType(type);
                    break;
            case R.id.provider:
                if (checked)
                    type = "Fournisseur";
                    user.setType(type);
                    break;
            case R.id.admin:
                if (checked)
                    type = "Admin";
                user.setType(type);
                break;
        }
    }


    // onClick du bouton S'INSCRIRE
    public void registerClick(View view) {


        if( email.getText().length() == 0 ){       // Si aucun email n'est entré
            email.setError("Entrez un email" );
        }

        if( password.getText().length() == 0){         // Si aucun password n'est entré
            password.setError("Entrez un mot de passe");
        }

        if( username.getText().length() == 0){         // Si aucun username n'est entré
            username.setError("Entrez un nom d'utilisateur");
        }

        if( name.getText().length() == 0){         // Si aucun prenom n'est entré
            name.setError("Entrez un prénom");
        }

        if( lastName.getText().length() == 0){         // Si aucun nom n'est entré
            lastName.setError("Entrez un nom de famille");
        }

        if (typeChoices.getCheckedRadioButtonId() == -1) {       //Si aucun type n'est selectioné
            askType.setText("Choisissez un type de compte");
        }

        else if(validateEmail() == false && validateUsername() == false){       // Si l'email et le username sont invalides
            email.setError("Compte existant");
            username.setError("Nom d'utilisateur existant");
        }

        else if(validateEmail() == false){                // Si l'email est invalide
            email.setError("Compte existant");
        }

        else if(validateUsername() == false){         // Si le username est invalide
            username.setError("Nom d'utilisateur existant");
        }

        else if (validateEmail() == true && validateUsername() == true) {
            // Ouvre l'activite de bienvenu après l'inscription
            if (typeChoices.getCheckedRadioButtonId() == R.id.user) {
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setUsername(username.getText().toString());
                myDataBase.addUser(user);
            }else if(typeChoices.getCheckedRadioButtonId() == R.id.provider){
                provider.setEmail(email.getText().toString());
                provider.setPassword(password.getText().toString());
                provider.setUsername(username.getText().toString());
                myDataBase.addProvider(provider);

            } else if(typeChoices.getCheckedRadioButtonId() == R.id.admin){
                  if(adminNumberAccount  == 0){
                      admin.setEmail(email.getText().toString());
                      admin.setPassword(password.getText().toString());
                      admin.setUsername(username.getText().toString());
                      myDataBase.addAdmin(admin);
                      adminNumberAccount++;
                  }else{
                      askType.setText("Choisissez un autre type dutilisateur , un seul compte Admin est déja crée vous ne pourriez pas créer un autre");
                  }


             }


            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivityForResult(intent, 0);


        }
    }

}
