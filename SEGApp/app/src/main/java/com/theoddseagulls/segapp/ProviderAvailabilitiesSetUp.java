package com.theoddseagulls.segapp;

        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v4.app.DialogFragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CalendarView;
        import android.widget.TextView;
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

    private TextView samError;
    private TextView dimError;
    private TextView lunError;
    private TextView marError;
    private TextView merError;
    private TextView jeuError;
    private TextView venError;


    private static  DB_handler mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_availabilities_set_up);

        pressed = " ";

        mydatabase = new DB_handler(this);

        //Buttons
        samFrom = findViewById(R.id.samFrom);
        samTo = findViewById(R.id.samTo);

        dimFrom = findViewById(R.id.dimFrom);
        dimTo = findViewById(R.id.dimTo);

        lunFrom = findViewById(R.id.lunFrom);
        lunTo = findViewById(R.id.lunTo);

        marFrom = findViewById(R.id.marFrom);
        marTo = findViewById(R.id.marTo);

        merFrom = findViewById(R.id.merFrom);
        merTo = findViewById(R.id.merTo);

        jeuFrom = findViewById(R.id.jeuFrom);
        jeuTo = findViewById(R.id.jeuTo);

        venFrom = findViewById(R.id.venFrom);
        venTo = findViewById(R.id.venTo);

        samError = findViewById(R.id.samError);
        dimError = findViewById(R.id.dimError);
        lunError = findViewById(R.id.lunError);
        marError = findViewById(R.id.marError);
        merError = findViewById(R.id.merError);
        jeuError = findViewById(R.id.jeuError);
        venError = findViewById(R.id.venError);

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
            samFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("samTo")){
            samTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("dimFrom")){
            dimFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("dimTo")){
            dimTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("lunFrom")){
            lunFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("lunTo")){
            lunTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("marFrom")){
            marFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("marTo")){
            marTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("merFrom")){
            merFrom.setText(hourOfDay + " : " + minute);
        }
        else if(pressed.equals("merTo")){
            merTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("jeuFrom")){
            jeuFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("jeuTo")){
            jeuTo.setText(hourOfDay + " h " + minute);
        }

        else if(pressed.equals("venFrom")){
            venFrom.setText(hourOfDay + " h " + minute);
        }
        else if(pressed.equals("venTo")){
            venTo.setText(hourOfDay + " h " + minute);
        }

    }

    public void saveClick(View view){

        if( !samFrom.equals("DE") && samTo.equals("À")){
            samError.setError("");
        }

        if(samFrom.equals("DE") && !samTo.equals("À")){
            dimError.setError("");
        }

        if( !dimFrom.equals("DE") && dimTo.equals("À")){
            dimError.setError("");
        }

        if(dimFrom.equals("DE") && !dimTo.equals("À")){
            samError.setError("");
        }





        else if ( (!samFrom.equals("DE") && !samTo.equals("À")) || (!dimFrom.equals("DE") && !dimTo.equals("À")) || (!lunFrom.equals("DE") && !lunTo.equals("À")) || (!marFrom.equals("DE") && !marTo.equals("À")) || (!merFrom.equals("DE") && !merTo.equals("À")) || (!jeuFrom.equals("DE") && !jeuTo.equals("À")) || (!venFrom.equals("DE") && !venTo.equals("À")) || (samFrom.equals("DE") && samTo.equals("À")) || (dimFrom.equals("DE") && dimTo.equals("À")) || (lunFrom.equals("DE") && lunTo.equals("À")) || (marFrom.equals("DE") && marTo.equals("À")) || (merFrom.equals("DE") && merTo.equals("À")) || (jeuFrom.equals("DE") && jeuTo.equals("À")) || (venFrom.equals("DE") && venTo.equals("À"))){
            Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);

            ProviderAccount provider = mydatabase.findUsernameProviderAccount(getIntent().getStringExtra("ACCOUNTUSERNAME"));

            intent.putExtra("ACCOUNTUSERNAME", provider.getUsername() );

            String samedi = "Samedi : " + samFrom.getText() + " - "  + samTo.getText();
            intent.putExtra("SAMEDI", samedi);
            provider.setSamedi(samedi);
          //  mydatabase.updateSamedi(provider.getUsername(), samedi);

            String dimanche = "Dimanche : " + dimFrom.getText() + " - "  + dimTo.getText();
            intent.putExtra("DIMANCHE", dimanche);
            provider.setDimanche(dimanche);
         //   mydatabase.updateDimanche(provider.getUsername(), dimanche);

            String lundi = "Lundi : " + lunFrom.getText() + " - "  + lunTo.getText();
            intent.putExtra("LUNDI", lundi);
            provider.setLundi(lundi);
          //  mydatabase.updateLundi(provider.getUsername(), lundi);

            String mardi = "Mardi : " + marFrom.getText() + " - "  + marTo.getText();
            intent.putExtra("MARDI", mardi);
            provider.setMardi(mardi);
          //  mydatabase.updateMardi(provider.getUsername(), mardi);

            String mercredi = "Mercredi : " + merFrom.getText() + " - "  + merTo.getText();
            intent.putExtra("MERCREDI", mercredi);
            provider.setMercredi(mercredi);
           // mydatabase.updateMercredi(provider.getUsername(), mercredi);

            String jeudi = "Jeudi : " + jeuFrom.getText() + " - "  + jeuTo.getText();
            intent.putExtra("JEUDI", jeudi);
            provider.setJeudi(jeudi);
          //  mydatabase.updateJeudi(provider.getUsername(), jeudi);

            String vendredi = "Vendredi : " + venFrom.getText() + " - "  + venTo.getText();
            intent.putExtra("VENDREDI", vendredi);
            provider.setVendredi(vendredi);
        //    mydatabase.updateVendredi(provider.getUsername(), vendredi);

            mydatabase.addProviderAvailabilities(provider);
            startActivityForResult(intent, 0);

        }

    }


}

