package com.theoddseagulls.segapp;

        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.support.v4.app.DialogFragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TimePicker;


public class ProviderAvailabilitiesSetUp extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {


    private String pressed;

    private Button samFrom;
    private Button samTo;
    private Button dimFrom;
    private Button dimTo;
    private Button lunFrom;
    private Button lunTo;
    private Button marFrom;
    private Button marTo;
    private Button merFrom;
    private Button merTo;
    private Button jeuFrom;
    private Button jeuTo;
    private Button venFrom;
    private Button venTo;

    private static  DB_handler mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_availabilities_set_up);

        pressed = " ";

        mydatabase = new DB_handler(this);

        //Buttons
        samFrom = findViewById(R.id.samFrom2);
        samTo = findViewById(R.id.samTo2);

        dimFrom = findViewById(R.id.dimFrom2);
        dimTo = findViewById(R.id.dimTo2);

        lunFrom = findViewById(R.id.lunFrom2);
        lunTo = findViewById(R.id.lunTo2);

        marFrom = findViewById(R.id.marFrom2);
        marTo = findViewById(R.id.marTo2);

        merFrom = findViewById(R.id.merFrom2);
        merTo = findViewById(R.id.merTo2);

        jeuFrom = findViewById(R.id.jeuFrom2);
        jeuTo = findViewById(R.id.jeuTo2);

        venFrom = findViewById(R.id.venFrom2);
        venTo = findViewById(R.id.venTo2);

    }



    public void samFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "samFrom";
    }

    public void samToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "samTo";
    }

    public void dimFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "dimFrom";
    }

    public void dimToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "dimTo";
    }

    public void lunFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "lunFrom";
    }

    public void lunToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "lunTo";
    }

    public void marFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "marFrom";
    }

    public void marToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "marTo";
    }

    public void merFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "merFrom";
    }

    public void merToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "merTo";
    }

    public void jeuFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "jeuFrom";
    }

    public void jeuToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "jeuTo";
    }

    public void venFromClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "venFrom";
    }

    public void venToClick(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Choix d'heure");

        pressed = "venTo";
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(pressed.equals("samFrom")){
            samFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("samTo")){
            samTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("dimFrom")){
            dimFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("dimTo")){
            dimTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("lunFrom")){
            lunFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("lunTo")){
            lunTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("marFrom")){
            marFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("marTo")){
            marTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("merFrom")){
            merFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("merTo")){
            merTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("jeuFrom")){
            jeuFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("jeuTo")){
            jeuTo.setText(hourOfDay + "h" + minute);
        }

        else if(pressed.equals("venFrom")){
            venFrom.setText(hourOfDay + "h" + minute);
        }
        else if(pressed.equals("venTo")){
            venTo.setText(hourOfDay + "h" + minute);
        }

    }

    public void savEClick(View view){

            Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

            ProviderAccount provider = mydatabase.findUsernameProviderAccount(getIntent().getStringExtra("ACCOUNTUSERNAME"));

            intent.putExtra("ACCOUNTUSERNAME", provider.getUsername());

            String samedi = "Samedi : " + samFrom.getText() + " - " + samTo.getText();
            intent.putExtra("SAMEDI", samedi);
            provider.setSamedi(samedi);
            mydatabase.updateSamedi(getIntent().getStringExtra("USERNAME"), samedi);

            String dimanche = "Dimanche : " + dimFrom.getText() + " - " + dimTo.getText();
            intent.putExtra("DIMANCHE", dimanche);
            provider.setDimanche(dimanche);
            mydatabase.updateDimanche(getIntent().getStringExtra("USERNAME"), dimanche);

            String lundi = "Lundi : " + lunFrom.getText() + " - " + lunTo.getText();
            intent.putExtra("LUNDI", lundi);
            provider.setLundi(lundi);
            mydatabase.updateLundi(getIntent().getStringExtra("USERNAME"), lundi);

            String mardi = "Mardi : " + marFrom.getText() + " - " + marTo.getText();
            intent.putExtra("MARDI", mardi);
            provider.setMardi(mardi);
            mydatabase.updateMardi(getIntent().getStringExtra("USERNAME"), mardi);

            String mercredi = "Mercredi : " + merFrom.getText() + " - " + merTo.getText();
            intent.putExtra("MERCREDI", mercredi);
            provider.setMercredi(mercredi);
            mydatabase.updateMercredi(getIntent().getStringExtra("USERNAME"), mercredi);

            String jeudi = "Jeudi : " + jeuFrom.getText() + " - " + jeuTo.getText();
            intent.putExtra("JEUDI", jeudi);
            provider.setJeudi(jeudi);
            mydatabase.updateJeudi(getIntent().getStringExtra("USERNAME"), jeudi);

            String vendredi = "Vendredi : " + venFrom.getText() + " - " + venTo.getText();
            intent.putExtra("VENDREDI", vendredi);
            provider.setVendredi(vendredi);
            mydatabase.updateVendredi(getIntent().getStringExtra("USERNAME"), vendredi);

            mydatabase.addProviderAvailabilities(provider);

            startActivityForResult(intent, 0);


    }


}

