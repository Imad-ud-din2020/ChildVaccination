package com.example.gauranggoel.childvacination;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
    DoctorAdapter(Activity activity,ArrayList<DoctorDetails> arrayList1)
    {
        super(activity,R.layout.doc_custom_list_view,arrayList1);
        this.activity=activity;
        this.arrayList=arrayList1;

        Log.d(TAG,"in constructor");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Log.d(TAG,"get view");

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.doc_custom_list_view,null);

       // Toast.makeText(activity, "in get View "+position, Toast.LENGTH_SHORT).show();
        TextView name=v.findViewById(R.id.list_doc_name);
        TextView hospital=v.findViewById(R.id.list_doc_hospital);
        Button button = v.findViewById(R.id.list_doc_call);

        name.setText(arrayList.get(position).getName());
        hospital.setText(arrayList.get(position).getHospital());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(activity, ""+position, Toast.LENGTH_SHORT).show();
                String s=arrayList.get(position).getPhone();
                if(s.length()==10)
                {
                    if(ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(activity,new String[]{android.Manifest.permission.CALL_PHONE},0);
                        return;
                    }

                    Intent i = new Intent(Intent.ACTION_DIAL);

                    i.setData(Uri.parse("tel:"+s));
                    activity.startActivity(i);
                }
                else{
                    Toast.makeText(activity, "Error Occured While Making Call,\n please try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
