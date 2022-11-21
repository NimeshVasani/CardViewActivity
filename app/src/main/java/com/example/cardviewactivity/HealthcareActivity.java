package com.example.cardviewactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cardviewactivity.Healthcare.BodyCareActivity;
import com.example.cardviewactivity.Healthcare.HairCareActivity;
import com.example.cardviewactivity.Healthcare.SkinCareActivity;
import com.example.cardviewactivity.Healthcare.WeightlossActivity;

public class HealthcareActivity extends AppCompatActivity {

    CardView weightloss,skincare,haircare,bodycare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare);

        weightloss=findViewById(R.id.weightloss);
        skincare=findViewById(R.id.skincare);
        haircare=findViewById(R.id.haircare);
        bodycare=findViewById(R.id.bodycare);

        weightloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(HealthcareActivity.this, WeightlossActivity.class);
                startActivity(i);

            }
        });

        skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HealthcareActivity.this, SkinCareActivity.class);
                startActivity(i);
            }
        });

        haircare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HealthcareActivity.this, HairCareActivity.class);
                startActivity(i);
            }
        });

        bodycare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HealthcareActivity.this, BodyCareActivity.class);
                startActivity(i);

            }
        });
    }
}
