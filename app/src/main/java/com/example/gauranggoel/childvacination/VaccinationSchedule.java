package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VaccinationSchedule extends AppCompatActivity {

Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_schedule);
        getSupportActionBar().hide();
        activity=this;
        ImageView img= (ImageView) findViewById(R.id.vaccine_schedule_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView tv= (TextView) findViewById(R.id.child_name_for_vaccinationSchedule);

        Bundle b = getIntent().getExtras();
        int position = Integer.parseInt(b.getString("position"));

        DatabaseChildDetails databaseChildDetails = new DatabaseChildDetails(this);

        ArrayList<ChildDetails> al = (ArrayList<ChildDetails>) databaseChildDetails.getAllRecords();

        String id=al.get(position).getId();

        tv.setText(al.get(position).getName());

        DatabaseVaccinationDetails databaseVaccinationDetails = new DatabaseVaccinationDetails(this);

        final ArrayList<VaccinationObject> arrayList= (ArrayList<VaccinationObject>) databaseVaccinationDetails.getAllRecords(id);

        ListView listView = (ListView) findViewById(R.id.vaccine_list_view);

        VaccinationDueAdapter adapter = new VaccinationDueAdapter(this,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VaccinationSchedule.this,UpdateVaccinationGivenTimeAndPlace.class);
                intent.putExtra("id",arrayList.get(position).getId());
                intent.putExtra("VaccineName",arrayList.get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
