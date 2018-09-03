package com.example.gauranggoel.childvacination;

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

import java.util.Calendar;
import java.util.List;

public class EditChildDetails extends AppCompatActivity {
    EditText et1,et2,et3;
    TextView et4;
    Button btn;
    String dob;
    int year;
    int month;
    int day;
    ImageView img,img1;
    DatabaseChildDetails databaseChildDetails;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child_details);
        databaseChildDetails = new DatabaseChildDetails(this);
        et1 = (EditText) findViewById(R.id.edit_child_name);
        et2 = (EditText) findViewById(R.id.edit_child_phone);
        et3 = (EditText) findViewById(R.id.edit_child_email);
        et4 = (TextView) findViewById(R.id.edit_child_dob);

        img = (ImageView) findViewById(R.id.edit_calendar);
        img1 = (ImageView) findViewById(R.id.edit_icon);

        btn = (Button) findViewById(R.id.edit_child_add);

        b = getIntent().getExtras();

        et1.setText(b.getString("name"));
        et2.setText(b.getString("phone"));
        et3.setText(b.getString("email"));
        et4.setText(b.getString("dob"));

        String da=b.getString("dob");

        String array[]=da.split("/");
        day=Integer.parseInt(array[0]);
        month=Integer.parseInt(array[1]);
        year=Integer.parseInt(array[2]);

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

                    List<ChildDetails> al= databaseChildDetails.getAllRecords();
                    ChildDetails child = databaseChildDetails.getSingleRecord(al.get(b.getInt("position")).getId());
                    child.setName(name);
                    child.setPhone(phone);
                    child.setEmail(email);
                    child.setDob(dob);
                    databaseChildDetails.updateRecord(child);
                    if(  MainActivity.activity!=null)
                        MainActivity.activity.finish();

                    Intent intent = new Intent(EditChildDetails.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditChildDetails.this,date,year,month,day).show();
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
