package com.theoddseagulls.segapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private static int adminNumberAccount = 0;
    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText username;
    private UserAccount user;
    private AdminAccount admin;
    private ProviderAccount provider;
    private String type;
    private RadioGroup typeChoices;
    private RadioButton adminBtn;
    private RadioButton userBtn;
    private RadioButton providerBtn;
    private TextView askType;
    private static DB_handler myDataBase;


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
        admin = AdminAccount.getTheaccount(email.getText().toString(), password.getText().toString());
        typeChoices = (RadioGroup) findViewById(R.id.typeOptions);
        adminBtn = (RadioButton) findViewById(R.id.admin);
        userBtn = (RadioButton) findViewById(R.id.user);
        providerBtn = (RadioButton) findViewById(R.id.provider);
        askType = (TextView) findViewById(R.id.enterType);
        myDataBase = new DB_handler(this);

        if(adminNumberAccount > 0) {
            adminBtn.setVisibility(View.INVISIBLE);
        }
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
                break;
            case R.id.provider:
                if (checked)
                    type = "Fournisseur";
                break;
            case R.id.admin:
                if (checked)
                    type = "Administrateur";
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
            if (userBtn.isChecked()) {
                user.setName(name.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setUsername(username.getText().toString());
                myDataBase.addUser(user);
            }

            else if(providerBtn.isChecked()){
                provider.setName(name.getText().toString());
                provider.setLastName(lastName.getText().toString());
                provider.setEmail(email.getText().toString());
                provider.setPassword(password.getText().toString());
                provider.setUsername(username.getText().toString());
                myDataBase.addProvider(provider);

            }

            else if(adminNumberAccount == 0 && adminBtn.isChecked()){
                    admin.setName(name.getText().toString());
                    admin.setLastName(lastName.getText().toString());
                    admin.setEmail(email.getText().toString());
                    admin.setPassword(password.getText().toString());
                    admin.setUsername(username.getText().toString());
                    myDataBase.addAdmin(admin);
                    adminNumberAccount++;
            }

            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);

            // Passe le username du User à la prochaine activité
            String accountUsername = myDataBase.findAccount(email.getText().toString()).getUsername();
            intent.putExtra("USERNAME", accountUsername);

            // Passe le type du user à la prochaine activité
            String accountType = myDataBase.findAccount(email.getText().toString()).getType();
            intent.putExtra("TYPE", accountType);

            startActivityForResult(intent, 0);

            // Toast pour indiquer que le user s'est connecté
            Context context = getApplicationContext();
            CharSequence text = "Connecté";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

}
