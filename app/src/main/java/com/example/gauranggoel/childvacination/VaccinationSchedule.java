package com.example.gauranggoel.childvacination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VaccinationSchedule extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_schedule);
        getSupportActionBar().hide();

        ImageView img= (ImageView) findViewById(R.id.vaccine_schedule_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Bundle b = getIntent().getExtras();
        int position = Integer.parseInt(b.getString("position"));

        DatabaseChildDetails databaseChildDetails = new DatabaseChildDetails(this);

        ArrayList<ChildDetails> al = (ArrayList<ChildDetails>) databaseChildDetails.getAllRecords();

        String id=al.get(position).getId();

        DatabaseVaccinationDetails databaseVaccinationDetails = new DatabaseVaccinationDetails(this);

        ArrayList<VaccinationObject> arrayList= (ArrayList<VaccinationObject>) databaseVaccinationDetails.getAllRecords(id);

        ListView listView = (ListView) findViewById(R.id.vaccine_list_view);

        VaccinationDueAdapter adapter = new VaccinationDueAdapter(this,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VaccinationSchedule.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
