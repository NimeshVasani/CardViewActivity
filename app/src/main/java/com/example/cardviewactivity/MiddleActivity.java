package com.example.cardviewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MiddleActivity extends AppCompatActivity {

    FirebaseAuth auth;
    String catagory;
    FirebaseFirestore firestore1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        auth=FirebaseAuth.getInstance();
        firestore1=FirebaseFirestore.getInstance();




    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            Intent i=new Intent(MiddleActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            String currid=user.getUid();


            firestore1.collection("CurrentUser").document(currid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {


                    if (documentSnapshot.exists()) {
                        /*progressDoalog = new ProgressDialog(MainActivity.this);
                        progressDoalog.setMax(100);
                        progressDoalog.setMessage("Loging...");
                        progressDoalog.setTitle("Plases wait untill we login your account...");
                        progressDoalog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
                        progressDoalog.show();
*/

                        catagory=documentSnapshot.getString("Catagory");
                        Toast.makeText(MiddleActivity.this,catagory, Toast.LENGTH_SHORT).show();


                        if(catagory.equals("Patient"))
                        {

                            Toast.makeText(MiddleActivity.this, catagory, Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MiddleActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else if (catagory.equals("Doctor")) {

                            Toast.makeText(MiddleActivity.this, catagory, Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MiddleActivity.this, PatientList2Activity.class);
                            startActivity(i);
                            finish();
                        } else if (catagory.equals("Nursing")) {

                            Toast.makeText(MiddleActivity.this, catagory, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MiddleActivity.this, NursingRequestActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(MiddleActivity.this, "Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MiddleActivity.this, "CurrentUser Collection Retrieving Failed", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
