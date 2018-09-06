package com.example.gauranggoel.childvacination;
//this activity is add details of child
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public static DatabaseChildDetails databaseChildDetails;
    public static DatabaseVaccinationDetails databaseVaccinationDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_details);

            getSupportActionBar().hide();
        databaseChildDetails = new DatabaseChildDetails(this);
        databaseVaccinationDetails=new DatabaseVaccinationDetails(this);
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
                    ChildDetails child = new ChildDetails(name,email,phone,dob);
                    databaseChildDetails.addRecord(child);
                    MainActivity.activity.finish();
                    String Id=dob+"/"+name+"/"+phone+"/"+email;
                    calculatingSchedule(Id);
                    Intent intent = new Intent(AddChildDetails.this,MainActivity.class);
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
    void add(String Id,String [] al,String time)
    {
        for(int i=0;i<al.length;i++)
        {
            VaccinationObject obj=new VaccinationObject(Id,al[i],time,"","NotGiven");
            Log.d("VaccineName",al[i]);
            databaseVaccinationDetails.addRecord(obj);
        }

    }

    void calculatingSchedule(String Id){
        String[] al = getResources().getStringArray(R.array.AtBirth);
        String time="";


        AddChildDetails addVaccine=new AddChildDetails();

        al=getResources().getStringArray(R.array.AtBirth);
        time=day+"/"+month+"/"+year;
        addVaccine.add(Id,al,time);


        al=getResources().getStringArray(R.array.Week6);
        time=calculateDate(45);
        addVaccine.add(Id,al,time);


        al=getResources().getStringArray(R.array.Week10);
        time=calculateDate(75);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.Week14);
        time=calculateDate(105);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.Month6);
        time=calculateMonth(6);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.Month9);
        time=calculateMonth(9);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.Month12);
        time=calculateMonth(12);
        addVaccine.add(Id,al,time);


        al=getResources().getStringArray(R.array.Month15);
        time=calculateMonth(15);
        addVaccine.add(Id,al,time);


        al=getResources().getStringArray(R.array.Month18);
        time=calculateMonth(18);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.year2);
        time=calculateYear(2);
        addVaccine.add(Id,al,time);


        al=getResources().getStringArray(R.array.year4);
        time=calculateYear(4);
        addVaccine.add(Id,al,time);

        al=getResources().getStringArray(R.array.year5);
        time=calculateYear(5);
        addVaccine.add(Id,al,time);

    }

    String  calculateDate(int days){


        int s1[]=new int[3];

        s1[0]=day;
        s1[1]=month;
        s1[2]=year;

        s1[0]+=days;
        int k=s1[0]/30;
        s1[0]%=31;
        s1[0]=s1[0]+1;

        s1[1]+=k;
        k=s1[1]/12;
        s1[1]%=12;
        s1[0]=s1[1]+1;

        s1[2]+=k;

        if(s1[1]==2)
        {
            if(s1[0] > 28)
            {
                s1[0]=s1[0]-28;
                s1[1]=3;
            }
        }
        else if(s1[1]==4 &&s1[1]==6 && s1[1]==9 &&s1[1]==11  )
        {
            if(s1[0]==31)
            {
                s1[0]=1;
                s1[1]+=1;
            }
        }


        return ""+s1[0]+"/"+s1[1]+"/"+s1[2];
    }


    String  calculateMonth(int month1) {
        int s1[]=new int[3];

        s1[0]=day;
        s1[1]=month;
        s1[2]=year;

        s1[1]+=month1;
        int k=s1[1]/12;
        s1[1]%=12;

        s1[2]+=k;

        return ""+s1[0]+"/"+s1[1]+"/"+s1[2];
    }

    String  calculateYear(int year1) {
        int s1[]=new int[3];

        s1[0]=day;
        s1[1]=month;
        s1[2]=year;

        s1[2]+=year1;

        return ""+s1[0]+"/"+s1[1]+"/"+s1[2];
    }

}
