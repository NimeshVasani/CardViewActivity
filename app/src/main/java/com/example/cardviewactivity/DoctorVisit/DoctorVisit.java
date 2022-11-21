package com.example.cardviewactivity.DoctorVisit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cardviewactivity.Adapter.DoctorUserAdapter;
import com.example.cardviewactivity.Model.DoctorVisitUser;

import com.example.cardviewactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DoctorVisit extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DoctorUserAdapter userAdapter;
    private List<DoctorVisitUser> mUsers;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_visit);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(DoctorVisit.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions((Activity) DoctorVisit.this,new String[]{Manifest.permission.CALL_PHONE},101);
            }
        }


        recyclerView=findViewById(R.id.doctor_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mUsers=new ArrayList<>();
        readDoctorUsers();

    }

    private void readDoctorUsers() {
        firestore=FirebaseFirestore.getInstance();
        firestore.collection("Doctor Visit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mUsers.clear();
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot snapshot:task.getResult())
                    {
                       DoctorVisitUser doctorVisitUser=new DoctorVisitUser(snapshot.getString("Name"),
                                                                            snapshot.getString("Expertization"),
                                                                            snapshot.getString("Degree"),
                                                                            snapshot.getString("Expirience"),
                                                                            snapshot.getString("Work Place"),
                                                                            snapshot.getString("Time"),
                                                                            snapshot.getString("Number"),
                                                                            snapshot.getString("Image"));
                       mUsers.add(doctorVisitUser);
                    }
                    userAdapter=new DoctorUserAdapter(getApplicationContext(),mUsers);
                    recyclerView.setAdapter(userAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DoctorVisit.this, "Data Retreiving Failed...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
