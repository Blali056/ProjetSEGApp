package com.theoddseagulls.segapp;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.CalendarView;
        import android.widget.TextView;


public class Availibilities extends AppCompatActivity {

    CalendarView calendarView ;
    TextView myDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availibilities);
        calendarView =  (CalendarView) findViewById(R.id.calendarView) ;
        myDate = (TextView) findViewById(R.id.myDate) ;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()  {
            @Override
            public void onSelectedDayChange( @NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date =  (i1+1)+"/"+i2+"/"+i ;
                myDate.setText(date);


            }
        })  ;

    }


    public void saveClick(View view){

        Intent intent = new Intent(getApplicationContext(), ProviderProfil.class);
        startActivityForResult(intent, 0);
    }
}
