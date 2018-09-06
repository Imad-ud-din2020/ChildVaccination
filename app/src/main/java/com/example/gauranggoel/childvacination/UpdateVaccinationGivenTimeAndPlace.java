package com.example.gauranggoel.childvacination;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateVaccinationGivenTimeAndPlace extends AppCompatActivity {

    TextView vaccine,schedule,given,childName;
    ImageView img1,img2,img3;
    EditText hospital,note,fee;
    Button btn1,btn2;
    DatabaseVaccinationDetails databaseVaccinationDetails;
    int year;
    int month;
    int day;
    String given_time;
    AlertDialog.Builder dialog;

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

        final VaccinationObject vaccinationObject=databaseVaccinationDetails.getSingleRecord(Id,vaccine_name);


        vaccine= (TextView) findViewById(R.id.vaccine_name);
        schedule= (TextView) findViewById(R.id.vaccine_schedule);
        given= (TextView) findViewById(R.id.vaccine_given);
        childName= (TextView) findViewById(R.id.child_name_for_VaccineUpdate1);

        vaccine.setText(vaccine_name);
        schedule.setText(vaccinationObject.getScheduleTime());
        childName.setText(child_name);

       // Toast.makeText(this, ""+vaccinationObject.getName(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, ""+vaccinationObject, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, ""+vaccinationObject.getGivenTime(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, ""+vaccinationObject.getStatus(), Toast.LENGTH_SHORT).show();

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


        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        given_time=day+"/"+month+"/"+year;

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s[]=vaccinationObject.getScheduleTime().split("/");

                dialog = new AlertDialog.Builder(UpdateVaccinationGivenTimeAndPlace.this);
                dialog.setTitle("Changing DOB");
                dialog.setMessage("Change In Schedule Time do not make changes in your Schedule, Better is not to change it");
                dialog.setPositiveButton("Ignore", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DatePickerDialog(UpdateVaccinationGivenTimeAndPlace.this,date1,year,month,day).show();
                        dialog.dismiss();
                    }
                });

                dialog.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        new DatePickerDialog(UpdateVaccinationGivenTimeAndPlace.this,date,year,month,day).show();
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
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year1, int month1, int day1) {

            given_time=day1+"/"+month1+"/"+year1;
            year=year1;
            day=day1;
            month=month1;
            given.setText(given_time);
        }
    };
    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year1, int month1, int day1) {

            given_time=day1+"/"+month1+"/"+year1;
            year=year1;
            day=day1;
            month=month1;
            given.setText(given_time);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
