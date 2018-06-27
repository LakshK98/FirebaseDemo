package com.example.szdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{
    private DatabaseReference mDatabase;
    private ArrayList<String> locations= new ArrayList<>();
    private ArrayAdapter<String> locationAdapter,localityAdapter;
    private Spinner spinner1,spinner2;
    private SparseArray<ArrayList<String>> localities = new SparseArray<>();
    private HashMap<String,PG> pgs = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getLocality(dataSnapshot.child("locality"));
                getLocation(dataSnapshot.child("location"));
                getPGs(dataSnapshot.child("pgs"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                localityAdapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_spinner_item,localities.get(i));

                localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(localityAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onSubmitClick();

            }
        });
    }

    void getLocation(DataSnapshot dataSnapshot)
    {


        for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            locations.add(ds.getValue(String.class));



        }


        locationAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(locationAdapter);
    }

    void getLocality(DataSnapshot dataSnapshot){
        for(DataSnapshot ds: dataSnapshot.getChildren())
        {

            int key=Integer.parseInt(ds.getKey());
            localities.append(key,new ArrayList<String>());

            for(DataSnapshot ds1: ds.getChildren())
            {
                localities.get(key).add(ds1.getValue(String.class));

            }

        }

    }

    void getPGs(DataSnapshot dataSnapshot){

        for(DataSnapshot ds: dataSnapshot.getChildren())
        {

            PG pg = ds.getValue(PG.class);
            pgs.put(ds.getKey(),pg);
        }

    }
    void onSubmitClick()
    {
        if(spinner1.getAdapter()==null || spinner2.getAdapter()==null)
            return;
        PG pg = pgs.get(spinner1.getSelectedItemPosition()+""+spinner2.getSelectedItemPosition());
        String selectedLocality=spinner2.getSelectedItem().toString();
        Intent i = new Intent(getBaseContext(),PGActivity.class);
        i.putExtra("locality",selectedLocality);
        i.putExtra("pgDetails",pg);
        startActivity(i);

    }
}
