package com.example.cardviewactivity.Healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.cardviewactivity.R;

public class WeightlossActivity extends AppCompatActivity {

    CardView diet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightloss);

        diet=findViewById(R.id.diet);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(WeightlossActivity.this,DietActivity.class);
                startActivity(i);
            }
        });
    }
}
