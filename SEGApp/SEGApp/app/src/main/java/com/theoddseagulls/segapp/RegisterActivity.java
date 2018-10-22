package com.theoddseagulls.segapp;
import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    String type;
    public DB_handler myDataBase;
    private RadioGroup radioGroup;
    private RadioButton user,supplier, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);
       // email = (EditText)findViewById(R.id.etEmail);
       // password = (EditText) findViewById(R.id.etPassword);
        myDataBase = new DB_handler(this);

       // radioGroup=(RadioGroup)findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
             //   if(checkedId==R.id.rbtnUsers){
                    type="user";
                    Toast.makeText(getApplicationContext(),"choice:User",Toast.LENGTH_SHORT).show();
                }
            //    else if(checkedId==R.id.rbtnFournisseur){
                 //   type="supplier";
                 //   Toast.makeText(getApplicationContext(), "choice: Supplier", Toast.LENGTH_SHORT).show();
               // }
              //  else if(checkedId==R.id.rbtnadmin){
                //    type="admin";
                //    Toast.makeText(getApplicationContext(), "choice: Admin", Toast.LENGTH_SHORT).show();
               // }
               // user=(RadioButton)findViewById(R.id.rbtnUsers);
               // supplier=(RadioButton)findViewById(R.id.rbtnFournisseur);
               // admin=(RadioButton)findViewById(R.id.rbtnadmin);



           // }
            public void newUser(View view) {
//
                User user = new User(email.getText().toString(), password.getText().toString(), type);
                if (myDataBase.findUser(user) != null) {
                    // we have to redirect to login page
                    Toast.makeText(getApplicationContext(), "This account is already registered", Toast.LENGTH_LONG).show();

                } else {
                    myDataBase.addUser(user);
                    email.setText("");
                    password.setText("");
                    radioGroup.clearCheck();
                }
            }










        });




    }
}
