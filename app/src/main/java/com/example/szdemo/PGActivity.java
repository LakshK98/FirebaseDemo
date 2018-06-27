package com.example.szdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PGActivity extends AppCompatActivity {
    private PG pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg);
        pg =(PG)getIntent().getSerializableExtra("pgDetails");
        TextView name,price,gender,locality,sharing;;
        ImageView img;
        name = findViewById(R.id.tv_name);
        price = findViewById(R.id.tv_price);
        gender = findViewById(R.id.tv_gender);
        sharing = findViewById(R.id.tv_sharing);
        locality = findViewById(R.id.tv_locality);
        img = findViewById(R.id.ic_gender);
        String temp;

        locality.setText(getIntent().getStringExtra("locality"));

        temp=pg.getName().toUpperCase();
        name.setText(temp);

        temp=" Starting from "+pg.getPrice();
        price.setText(temp);

        temp= "Available sharing: " +pg.getSharing();
        sharing.setText(temp);

        String sex=pg.getGender();
        if(sex.equals("male"))
        {
            gender.setText(sex);
            img.setImageResource(R.drawable.ic_man);
        }
        else if(sex.equals("female"))
        {
            gender.setText(sex);
            img.setImageResource(R.drawable.ic_girl);

        }
        else
        {
            gender.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
        }

        CardView card =findViewById(R.id.card_layout);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),PGExpandedActivity.class);
                i.putExtra("address",pg.getAddress());
                i.putExtra("contact",pg.getContact());
                startActivity(i);
            }
        });
    }
}
