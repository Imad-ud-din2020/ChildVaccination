package com.example.gauranggoel.childvacination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdateVaccinationGivenTimeAndPlace extends AppCompatActivity {

    TextView vaccine,schedule,given,childName;
    ImageView img1,img2,img3;
    EditText hospital,note,fee;
    Button btn1,btn2;
    DatabaseVaccinationDetails databaseVaccinationDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vaccination_given_time_and_place);
        getSupportActionBar().hide();

        databaseVaccinationDetails= new DatabaseVaccinationDetails(this);

        Bundle b = getIntent().getExtras();
        String Id = b.getString("id");
        String vaccine_name = b.getString("VaccineName");
        String child_name = b.getString("UserName");

        VaccinationObject vaccinationObject=databaseVaccinationDetails.getSingleRecord(Id,vaccine_name);

        vaccine= (TextView) findViewById(R.id.vaccine_name);
        schedule= (TextView) findViewById(R.id.vaccine_schedule);
        given= (TextView) findViewById(R.id.vaccine_given);
        childName= (TextView) findViewById(R.id.child_name_for_VaccineUpdate1);

        schedule.setText(vaccinationObject.getScheduleTime());

        img1= (ImageView) findViewById(R.id.vaccine_schedule_icon1);
        img2= (ImageView) findViewById(R.id.schedule_calendar);
        img3= (ImageView) findViewById(R.id.given_calendar);

        hospital= (EditText) findViewById(R.id.hospital_name);
        fee= (EditText) findViewById(R.id.vaccine_fee);
        note= (EditText) findViewById(R.id.notes);

        btn1= (Button) findViewById(R.id.vaccine_update);
        btn2= (Button) findViewById(R.id.vaccine_cancel);


        childName.setText(child_name);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update code goes here
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
