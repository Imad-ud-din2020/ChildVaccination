package com.example.gauranggoel.childvacination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VaccinationSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_schedule);
        getSupportActionBar().hide();
    }
}
