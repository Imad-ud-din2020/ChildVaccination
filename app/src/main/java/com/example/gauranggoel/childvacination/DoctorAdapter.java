package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gaurang goel on 02-09-2018.
 */

public class DoctorAdapter extends ArrayAdapter {
    Activity activity;
    ArrayList<DoctorDetails> arrayList;
public static final String  TAG="DoctorAdapter";
    DoctorAdapter(Activity activity,ArrayList<DoctorDetails> arrayList)
    {
        super(activity,R.layout.doc_custom_list_view,arrayList);
        this.activity=activity;
        this.arrayList=arrayList;

        Log.d(TAG,"in constructor");
    }

    @Override
    public View getView( int position,View convertView, ViewGroup parent) {


        Log.d(TAG,"get view");

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.doc_custom_list_view,null);

        Toast.makeText(activity, "in get View "+position, Toast.LENGTH_SHORT).show();
        TextView name=v.findViewById(R.id.list_doc_name);
        TextView hospital=v.findViewById(R.id.list_doc_hospital);
       // Button button = v.findViewById(R.id.list_doc_call);

        name.setText(arrayList.get(position).getName());
        hospital.setText(arrayList.get(position).getHospital());
      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, ""+position, Toast.LENGTH_SHORT).show();
            }
        });*/
        return v;
    }
}
