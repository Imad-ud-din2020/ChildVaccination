package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gaurang goel on 03-09-2018.
 */

public class VaccinationDueAdapter extends ArrayAdapter {

    Activity activity;
    ArrayList<VaccinationObject> arrayList;

    public VaccinationDueAdapter(Activity activity,ArrayList<VaccinationObject> arrayList){
        super(activity,R.layout.vaccine_due_layout,arrayList);
        this.activity=activity;
        this.arrayList=arrayList;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        TextView name,schedule,given,status;

        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.vaccine_due_layout,null);

        name=v.findViewById(R.id.child_vaccine_name);
        schedule=v.findViewById(R.id.child_schedule_time);
        given=v.findViewById(R.id.child_given_time);
        status=v.findViewById(R.id.child_status);

        name.setText(arrayList.get(position).getName());
        schedule.setText(arrayList.get(position).getScheduleTime());
        given.setText(arrayList.get(position).getGivenTime());
        status.setText(arrayList.get(position).getStatus());

        return v;
    }
}
