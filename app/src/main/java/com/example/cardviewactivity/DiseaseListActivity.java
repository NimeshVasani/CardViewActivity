package com.example.cardviewactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DiseaseListActivity extends AppCompatActivity {

   CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15;
   public static String disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_list);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        c7=findViewById(R.id.c7);
        c8=findViewById(R.id.c8);
        c9=findViewById(R.id.c9);
        c10=findViewById(R.id.c10);
        c11=findViewById(R.id.c11);
        c12=findViewById(R.id.c12);
        c13=findViewById(R.id.c13);
        c14=findViewById(R.id.c14);
        c15=findViewById(R.id.c15);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                 disease="Women's issues";
                startActivity(i);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
               disease="General Physician";
                startActivity(i);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Sexual Problems";
                startActivity(i);
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
               disease="Stress & Mental Health";
                startActivity(i);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Skin Problems";
                startActivity(i);
            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Hair and Scalp Problems";
                startActivity(i);
            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Pregnancy Problems";
                startActivity(i);
            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Conceiving issues";
                startActivity(i);
            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Child Infant issues";
                startActivity(i);
            }
        });

        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Acidity,Gas,Stomach issues";
                startActivity(i);
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Neologury";
                startActivity(i);
            }
        });

        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Orthopedics-Bones,Joints issues";
                startActivity(i);
            }
        });

        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Cancer Advice";
                startActivity(i);
            }
        });

        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Diabetes Consult";
                startActivity(i);
            }
        });

        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiseaseListActivity.this,DoctorListActivity.class);
                disease="Cardiac issue-Heart related issues";
                startActivity(i);
            }
        });

    }
}
