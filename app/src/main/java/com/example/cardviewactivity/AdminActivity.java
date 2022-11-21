package com.example.cardviewactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    CardView adddoctor,addnursing,deletedoctor,deletenursing,deletepatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adddoctor=findViewById(R.id.adddoctor);
        addnursing=findViewById(R.id.addnurssing);
        deletedoctor=findViewById(R.id.deletedoctor);
        deletenursing=findViewById(R.id.deletenurssing);
        deletepatient=findViewById(R.id.deletpatient);

        adddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AdminActivity.this,AddDoctorAndNursingActivity.class);
                startActivity(i);

            }
        });

        addnursing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AdminActivity.this,AddNursingActivity.class);
                startActivity(i);
            }
        });

        deletedoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AdminActivity.this,DeleteUserActivity.class);
                startActivity(i);
            }
        });
        deletenursing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AdminActivity.this,DeleteUserActivity.class);
                startActivity(i);
            }
        });
        deletepatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AdminActivity.this,DeleteUserActivity.class);
                startActivity(i);
            }
        });

    }
}
