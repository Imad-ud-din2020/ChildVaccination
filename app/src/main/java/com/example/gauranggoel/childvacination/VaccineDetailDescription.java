package com.example.gauranggoel.childvacination;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VaccineDetailDescription extends Fragment {


    public static final String TAG="VaccineDetailsDescription";
    public VaccineDetailDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_vaccine_detail_description, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Bundle b = getArguments();

        TextView name = v.findViewById(R.id.vaccineName);
        TextView description = v.findViewById(R.id.vaccineDescription);

        List al = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.vaccine_name)));
        List al1 = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.vaccine_detail)));

        //Toast.makeText(getActivity().getApplicationContext(), ""+getArguments(), Toast.LENGTH_SHORT).show();
        String num=b.getString("number");
        Log.d(TAG,"getting value"+num);
        name.setText(""+al.get(Integer.parseInt(num)));
        description.setText("" +al1.get(Integer.parseInt(num)));

        ImageView img = v.findViewById(R.id.Vacinicon_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.vaccineDetails,new ShowingVaccineDetails());
                //ft.addToBackStack("VaccinDetailsDescription");
                ft.commit();
            }
        });

        return v;
    }



}
