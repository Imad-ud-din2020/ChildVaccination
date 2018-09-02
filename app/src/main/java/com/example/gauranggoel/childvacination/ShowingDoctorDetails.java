package com.example.gauranggoel.childvacination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowingDoctorDetails extends AppCompatActivity {

    ListView listView;
    DoctorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_doctor_details);
        getSupportActionBar().hide();

        ArrayList<DoctorDetailis> arrayList= (ArrayList<DoctorDetailis>) DoctorDetailis.doctorDetailises;

        String[] s = new String[arrayList.size()];

        for(int i=0;i<s.length;i++)
        {
            s[i]=arrayList.get(i).getName();
        }

        listView= (ListView) findViewById(R.id.doc_list);

        adapter=new DoctorAdapter(this,arrayList,s);

        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
