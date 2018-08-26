package com.example.gauranggoel.childvacination;
//this activity is add details of child
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddChildDetails extends AppCompatActivity {

    EditText et1,et2,et3;
    TextView et4;
    Button btn;
    String dob;
    int year;
    int month;
    int day;
    ImageView img,img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_details);

            getSupportActionBar().hide();

         et1 = (EditText) findViewById(R.id.child_name);
         et2 = (EditText) findViewById(R.id.child_phone);
         et3 = (EditText) findViewById(R.id.child_email);
         et4 = (TextView) findViewById(R.id.child_dob);
        img = (ImageView) findViewById(R.id.calendar);
        img1 = (ImageView) findViewById(R.id.icon);

        btn = (Button) findViewById(R.id.child_add);

        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        dob=day+"/"+month+"/"+year;

        et4.setText(dob);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et1.getText().toString();
                String phone=et2.getText().toString();
                String email=et3.getText().toString();
                if(!et4.getText().toString().equals(""))
                    dob=et4.getText().toString();

                if(name.equals(""))
                    et1.setError("Please Enter Name");

                if(phone.length()!=10)
                    et2.setError("Please Enter correct Phone Number");

          //      Toast.makeText(AddChildDetails.this, ""+phone.length(), Toast.LENGTH_SHORT).show();

                if(phone.equals(""))
                    et2.setError("Please Enter Phone Number");

                if(!email.contains("@"))
                    et3.setError("Please Enter Correct Email");

                if(email.equals(""))
                    et3.setError("Please Enter Email");

                if(!name.equals("") && !phone.equals("") && !email.equals("") && email.contains("@") && phone.length()==10)
                {
                    ArrayList<ChildDetails> al = ChildDetails.getAl();
                    ChildDetails child = new ChildDetails(name,email,phone,dob);
                    al.add(child);
                    Intent intent = new Intent(AddChildDetails.this,MainActivity.class);
                    //Toast.makeText(AddChildDetails.this, ""+al, Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddChildDetails.this, "Press Long To Edit Details Of Child", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                 }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddChildDetails.this,date,year,month,day).show();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

     DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year1, int month1, int day1) {

            dob=day1+"/"+month1+"/"+year1;
            year=year1;
            day=day1;
            month=month1;
            et4.setText(dob);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
