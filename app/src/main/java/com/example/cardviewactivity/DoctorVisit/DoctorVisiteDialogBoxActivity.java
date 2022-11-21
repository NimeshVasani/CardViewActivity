package com.example.cardviewactivity.DoctorVisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.cardviewactivity.R;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

public class DoctorVisiteDialogBoxActivity extends AppCompatActivity {

    String name,degree,expertization,experience,time,mobilenumber,workplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_visite_dialog_box);

        name=getIntent().getStringExtra("name");
        degree=getIntent().getStringExtra("degree");
        expertization=getIntent().getStringExtra("expertization");
        experience=getIntent().getStringExtra("experience");
        time=getIntent().getStringExtra("time");
        mobilenumber=getIntent().getStringExtra("mobilenumber");
        workplace=getIntent().getStringExtra("workplace");


        new FancyGifDialog.Builder(DoctorVisiteDialogBoxActivity.this)
                .setTitle(name)
                .setMessage("Expertization : "+expertization+"\n\n"+"Degree : "+degree+"\n\n"+"Experience : "+experience+"\n\n"+"Time : "+time+"\n\n"+"Contact : "+mobilenumber+"\n\n"+"Working Place : "+workplace)
                .setPositiveBtnText("Call")
                .setPositiveBtnBackground(R.color.colorAccen)

                .setNegativeBtnBackground(R.color.colorPrimary)
                .setGifResource(R.drawable.ic_launcher_background)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+mobilenumber));
                        startActivity(callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    }
                })

                .build();

    }

}
