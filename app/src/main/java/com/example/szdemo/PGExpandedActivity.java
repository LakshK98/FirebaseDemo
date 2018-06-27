package com.example.szdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PGExpandedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgexpanded);

        TextView address,contact;
        address=findViewById(R.id.tv_address);
        contact=findViewById(R.id.tv_contact);

        Intent i =getIntent();
        String temp;

        temp =i.getStringExtra("address");
        temp.toUpperCase();
        address.setText(temp);

        temp=String.valueOf(i.getLongExtra("contact",1));
        contact.setText(temp);



    }
}
