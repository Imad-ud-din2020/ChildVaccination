package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowingDoctorDetails extends AppCompatActivity {

    ListView listView;
    DoctorAdapter adapter;
    DatabaseDoctorDetails databaseDoctorDetails;
    public static Activity activity;
    int longClickedPosition=-1;
    ArrayList<DoctorDetails> arrayList;
    public static final String TAG="ShowingDoctorDetails";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_doctor_details);
        getSupportActionBar().hide();
        activity = this;
        databaseDoctorDetails=new DatabaseDoctorDetails(this);


        Log.d(TAG,"on Create");

        //Floating Action Bar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_doc_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG,"float action bar");
                Intent intent = new Intent(ShowingDoctorDetails.this,AddDoctorDetail.class);
                startActivity(intent);

            }
        });

        arrayList= (ArrayList<DoctorDetails>) databaseDoctorDetails.getAllRecords();

        listView= (ListView) findViewById(R.id.doc_list_item);
        Log.d(TAG,"context menu registered");

        //Toast.makeText(activity, ""+arrayList.size(), Toast.LENGTH_SHORT).show();

        adapter=new DoctorAdapter(this,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedPosition=position;

                Log.d(TAG,"context Listener");
                return false;
            }
        });
 /*       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ShowingDoctorDetails.this, ""+position, Toast.LENGTH_SHORT).show();

                Log.d(TAG,"on item Click");

            }
        });*/

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
        Log.d(TAG,"onCreateContextMenu");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("Delete"))
        {
            databaseDoctorDetails.deleteRecord(arrayList.get(longClickedPosition).getId());
            arrayList.remove(longClickedPosition);
            adapter.notifyDataSetChanged();

            Log.d(TAG,"delete");
        }
        else  if(item.getTitle().equals("Edit"))
        {
            Intent intent= new Intent(ShowingDoctorDetails.this,EditDoctorDetails.class);
            DoctorDetails doctor = arrayList.get(longClickedPosition);
            intent.putExtra("name",doctor.getName());
            intent.putExtra("phone",doctor.getPhone());
            intent.putExtra("hospital",doctor.getHospital());
            intent.putExtra("position",longClickedPosition);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}