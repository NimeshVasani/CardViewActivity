package com.example.cardviewactivity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EmergencyActivity extends AppCompatActivity {
    ImageView corona,police,fire,ambulance,childhelpline,seniorcitizenhelpline,blood,womenhelpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        corona=findViewById(R.id.corona);
        police=findViewById(R.id.police);
        fire=findViewById(R.id.fire);
        ambulance=findViewById(R.id.ambulance);
        childhelpline=findViewById(R.id.childhelpline);
        seniorcitizenhelpline=findViewById(R.id.citizen);
        womenhelpline=findViewById(R.id.womenhelpline);
        blood=findViewById(R.id.blood);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(EmergencyActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions((Activity) EmergencyActivity.this,new String[]{Manifest.permission.CALL_PHONE},101);
            }
        }

        corona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"+911123978046"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"100"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"101"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"108"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        childhelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"1098"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });
        seniorcitizenhelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"18001801253"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"02782561526"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        womenhelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"181"));
                startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }
}
