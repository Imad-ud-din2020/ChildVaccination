package com.example.gauranggoel.childvacination;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gaurang goel on 26-08-2018.
 */

public class CustomAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<ChildDetails> al;

    public CustomAdapter( Activity context , ArrayList<ChildDetails> al) {
        super(context, R.layout.custom_list_view,al);
        this.context =context;
        this.al=al;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = context.getLayoutInflater();

        View v= inflater.inflate(R.layout.custom_list_view,null);

        TextView name=v.findViewById(R.id.list_name);
        TextView dob=v.findViewById(R.id.list_dob);

        name.setText(al.get(position).getName());
        dob.setText(al.get(position).getDob());
        return v;
    }


}
