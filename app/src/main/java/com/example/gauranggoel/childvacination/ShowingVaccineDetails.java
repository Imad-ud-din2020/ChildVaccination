package com.example.gauranggoel.childvacination;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
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
            }
        });


        return v;
    }

    void callingFragment(int k){
        VaccineDetailDescription obj = new VaccineDetailDescription();
        Bundle b = new Bundle();
        b.putString("num",""+k);
        obj.setArguments(b);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.vaccineDetails,new VaccineDetailDescription());
        ft.commit();
    }

}
