package com.example.gauranggoel.childvacination;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class EditDoctorDetails extends AppCompatActivity {

    EditText et1,et2,et3;
    DatabaseDoctorDetails databaseDoctorDetails = new DatabaseDoctorDetails(this);
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_details);
        getSupportActionBar().hide();

        et1= (EditText) findViewById(R.id.edit_doc_name);
        et2= (EditText) findViewById(R.id.edit_doc_phone);
        et3= (EditText) findViewById(R.id.edit_doc_hospital);

        b = getIntent().getExtras();

        et1.setText(b.getString("name"));
        et2.setText(b.getString("phone"));
        et3.setText(b.getString("hospital"));


        ImageView img = (ImageView) findViewById(R.id.edit_doc_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Button btn = (Button) findViewById(R.id.edit_doc_submit);

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
                        et2.setError("Please Enter Phone number");
                    if(hospital.equals(""))
                        et3.setError("Please Enter Name");
                }
                else{


                    List<DoctorDetails> al= databaseDoctorDetails.getAllRecords();
                    DoctorDetails doctorDetails = databaseDoctorDetails.getSingleRecord(al.get(b.getInt("position")).getId());
                    doctorDetails.setName(name);
                    doctorDetails.setPhone(phone);
                    doctorDetails.setHospital(hospital);
                    databaseDoctorDetails.updateRecord(doctorDetails);
                    ShowingDoctorDetails.activity.finish();
                    Intent intent = new Intent(EditDoctorDetails.this,ShowingDoctorDetails.class);
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
