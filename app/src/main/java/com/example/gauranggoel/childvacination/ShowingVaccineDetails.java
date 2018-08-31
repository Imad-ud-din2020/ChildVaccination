package com.example.gauranggoel.childvacination;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowingVaccineDetails extends Fragment {


    public static final String TAG="showingVaccineDetailis";
    public ShowingVaccineDetails() {
        // Required empty public constructor
    }
    List al;
AutoCompleteTextView autoCompleteTextView;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_showing_vaccine_details, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        //getting items in arraylist so that it can be added further
        String name[]=getResources().getStringArray(R.array.vaccine_name);
        al=new ArrayList(Arrays.asList(name));


        autoCompleteTextView= v.findViewById(R.id.autoTextComplete);
        autoCompleteTextView.setThreshold(1);
        ArrayAdapter adapter= new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,al);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=autoCompleteTextView.getText().toString();

                int k=-1;
                for(int i=0;i<al.size();i++)
                {
                    if(s.equals(al.get(i)))
                        k=i;
                }
                Log.d(TAG,"inside autocomplete textview"+k);

                callingFragment(k);
            }
        });



        FloatingActionButton fab1 = (FloatingActionButton) v.findViewById(R.id.addingVaccineDetails);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        listView = v.findViewById(R.id.listViewShowingDetails);
        ArrayAdapter adapter1= new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,al);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callingFragment(position);
                Toast.makeText(getActivity().getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();

                Log.d(TAG,"inside listview"+position);
            }
        });


        return v;
    }




    void callingFragment(int k){
        VaccineDetailDescription obj = new VaccineDetailDescription();
        Bundle b = new Bundle();
        b.putString("number",String.valueOf(k));
        obj.setArguments(b);
        Log.d(TAG,"inside calling fragment");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.vaccineDetails,obj);
        //ft.addToBackStack("VaccinDetailsDescription");
        ft.commit();
    }

}
