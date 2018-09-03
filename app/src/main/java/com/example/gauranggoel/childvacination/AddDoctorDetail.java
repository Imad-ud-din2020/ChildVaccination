package com.example.gauranggoel.childvacination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddDoctorDetail extends AppCompatActivity {

    EditText et1,et2,et3;
    DatabaseDoctorDetails databaseDoctorDetails ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_detail);
        getSupportActionBar().hide();

        et1= (EditText) findViewById(R.id.doc_name);
        et2= (EditText) findViewById(R.id.doc_phone);
        et3= (EditText) findViewById(R.id.doc_hospital);

        databaseDoctorDetails = new DatabaseDoctorDetails(this);
        ImageView img = (ImageView) findViewById(R.id.doc_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Button btn = (Button) findViewById(R.id.doc_submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                String phone= et2.getText().toString();
                String hospital=et3.getText().toString();

                if(name.equals("") || phone.equals("") || hospital.equals("") || phone.length()!=10){
                    if(name.equals(""))
                        et1.setError("Please Enter Name");
                    if(phone.equals("") || phone.length()!=10)
                        et2.setError("Please Enter Correct Phone number");
                    if(hospital.equals(""))
                        et3.setError("Please Enter Hospital Name");
                }
                else{

                    DoctorDetails doc = new DoctorDetails(name,phone,hospital);

                    databaseDoctorDetails.addRecord(doc);

                   if( ShowingDoctorDetails.activity!=null)
                       ShowingDoctorDetails.activity.finish();
                    Toast.makeText(AddDoctorDetail.this, "Doctors's Detail Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddDoctorDetail.this,ShowingDoctorDetails.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


