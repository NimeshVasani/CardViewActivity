package com.example.cardviewactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class AddNursingActivity extends AppCompatActivity {

    MaterialEditText name,workplace,degree,expertization,expirience,number,email,password,repassword,starttime,endtime;

    RadioButton male,female,doctorvisit,homeservice;

    Button submit;

    String gender;

    String catagory;

    AlertDialog waitingDialog;



    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nursing);

        name=findViewById(R.id.name);
        workplace=findViewById(R.id.workplace);
        starttime=findViewById(R.id.starttime);
        endtime=findViewById(R.id.endtime);
        degree=findViewById(R.id.degree);
        expertization=findViewById(R.id.expertization);
        expirience=findViewById(R.id.experience);
        number=findViewById(R.id.number);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);


        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        doctorvisit=findViewById(R.id.doctorvisit);
        homeservice=findViewById(R.id.homeservice);

        submit=findViewById(R.id.submit);

        firestore=FirebaseFirestore.getInstance();

        waitingDialog=new AlertDialog.Builder(this).setMessage("Adding Nursing Member...").setCancelable(true).create();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {





                final String sname=name.getText().toString().trim();
                String sworkplace=workplace.getText().toString().trim();
                String sstarttime=starttime.getText().toString().trim();
                String sendtime=endtime.getText().toString().trim();
                String sdegree=degree.getText().toString().trim();
                String sexpertization=expertization.getText().toString().trim();
                String sexpirience=expirience.getText().toString().trim();
                final String snumber=number.getText().toString().trim();
                final String semail=email.getText().toString().trim();
                final String spassword=password.getText().toString().trim();
                String srepassword=repassword.getText().toString().trim();




                if(sname.isEmpty())
                {
                    name.setError("Enter Your Name");
                    name.setErrorColor(R.color.colorPrimaryDark);

                }
                else if(sworkplace.isEmpty())
                {
                    workplace.setError("Enter Work Place");
                    workplace.setErrorColor(R.color.error);
                }
               else if(sstarttime.isEmpty())
                {
                    starttime.setError("Enter Start Time");
                    starttime.setErrorColor(R.color.error);
                }
                else if(sendtime.isEmpty())
                {
                    endtime.setError("Enter End Time");
                    endtime.setErrorColor(R.color.error);
                }
                else if(Integer.parseInt(sstarttime) < 1 && Integer.parseInt(sstarttime)>24)
                {
                    endtime.setError("Start Time Must Be Grater Than 0 and Less Than 24");
                    endtime.setErrorColor(R.color.error);
                }
                else if(Integer.parseInt(sendtime) < 1 && Integer.parseInt(sendtime)>24)
                {
                    endtime.setError("End Time Must Be Grater Than 0 and Less Than 24");
                    endtime.setErrorColor(R.color.error);

                }
                else if(Integer.parseInt(sendtime)<Integer.parseInt(sstarttime))
                {
                    endtime.setError("End time Must Be Grater Then Starting Time");
                    endtime.setErrorColor(R.color.error);
                    starttime.setError("End time Must Be Grater Then Starting Time");
                    starttime.setErrorColor(R.color.error);

                }
                else if(sdegree.isEmpty())
                {
                    degree.setError("Enter Degree");
                    degree.setErrorColor(R.color.error);
                }
                else if(sexpertization.isEmpty())
                {
                    degree.setError("Enter Expertization");
                    degree.setErrorColor(R.color.error);
                }
                else if(sexpirience.isEmpty())
                {
                    degree.setError("Enter Expirience");
                    degree.setErrorColor(R.color.error);
                }

                else if(snumber.isEmpty())
                {
                    number.setError("Enter Your ");
                    number.setErrorColor(R.color.error);
                }
                else if(semail.isEmpty())
                {
                    email.setError("Enter Your Email");
                    email.setErrorColor(R.color.error);
                }
                else if(spassword.isEmpty())
                {
                    password.setError("Enter Your Password ");
                    password.setErrorColor(R.color.error);
                }
                else if(srepassword.isEmpty())
                {
                    repassword.setError("Enter Your Re-password ");
                    repassword.setErrorColor(R.color.error);
                }
                else if(!male.isChecked() && !female.isChecked())
                {
                    Toast.makeText(AddNursingActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                }
                else if(!doctorvisit.isChecked() && !homeservice.isChecked())
                {
                    Toast.makeText(AddNursingActivity.this, "Please Select Catagory", Toast.LENGTH_SHORT).show();
                }
                else
                {



                    if(male.isChecked())
                    {
                        gender="male";
                    }
                    else
                    {
                        gender="female";

                    }
                    if(doctorvisit.isChecked())
                    {
                        catagory="Doctor Visit";


                        if(spassword.length()>=6) {
                            if (spassword.equals(srepassword)) {

                                waitingDialog.show();

                                final HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("Name", sname);
                                hashMap.put("Work Place", sworkplace);
                                hashMap.put("Time", sstarttime+" TO "+sendtime);
                                hashMap.put("Degree",sdegree);
                                hashMap.put("Expertization",sexpertization);
                                hashMap.put("Expirience",sexpirience);
                                hashMap.put("Number", snumber);
                                hashMap.put("Email", semail);
                                hashMap.put("Password", spassword);
                                hashMap.put("Gender", gender);
                                hashMap.put("Catagory",catagory);
                                hashMap.put("Image","default");



                                firestore.collection("Doctor Visit").document(semail).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        HashMap<String, Object> hashMap1 = new HashMap<>();
                                        hashMap1.put("Catagory","Nursing");
                                        hashMap1.put("Email",semail);
                                        hashMap1.put("Number",snumber);
                                        hashMap1.put("Password",spassword);
                                        hashMap1.put("Name",sname);

                                        firestore.collection("Users").document(semail).set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                              waitingDialog.dismiss();
                                                Toast.makeText(AddNursingActivity.this, "Nursing add Successfully", Toast.LENGTH_SHORT).show();
                                                Intent i=new Intent(AddNursingActivity.this,AdminActivity.class);
                                                startActivity(i);
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                              waitingDialog.dismiss();
                                                Toast.makeText(AddNursingActivity.this, "Data Not Inserted Users Collection", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                       waitingDialog.dismiss();
                                        Toast.makeText(AddNursingActivity.this, "Data Not Inserted in Doctor Collection", Toast.LENGTH_SHORT).show();

                                    }
                                });


                            }
                            else
                            {
                                Toast.makeText(AddNursingActivity.this, "Password Dissmatch", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(AddNursingActivity.this, "Passwords Must be Greater then 5 character", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(homeservice.isChecked())
                    {
                        catagory="Home Service";


                        if(spassword.length()>=6) {
                            if (spassword.equals(srepassword)) {
                               waitingDialog.show();
                                final HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("Name", sname);
                                hashMap.put("Work Place", sworkplace);
                                hashMap.put("Time", sstarttime+" TO "+sendtime);
                                hashMap.put("Degree",sdegree);
                                hashMap.put("Expertization",sexpertization);
                                hashMap.put("Expirience",sexpirience);
                                hashMap.put("Number", snumber);
                                hashMap.put("Email", semail);
                                hashMap.put("Password", spassword);
                                hashMap.put("Gender", gender);
                                hashMap.put("Catagory",catagory);
                                hashMap.put("Image","default");



                                firestore.collection("Home Service").document(semail).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        HashMap<String, Object> hashMap1 = new HashMap<>();
                                        hashMap1.put("Catagory","Nursing");
                                        hashMap1.put("Email",semail);
                                        hashMap1.put("Number",snumber);
                                        hashMap1.put("Password",spassword);
                                        hashMap1.put("Name",sname);

                                        firestore.collection("Users").document(semail).set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               waitingDialog.dismiss();
                                                Toast.makeText(AddNursingActivity.this, "Nursing add Successfully", Toast.LENGTH_SHORT).show();
                                                Intent i=new Intent(AddNursingActivity.this,AdminActivity.class);
                                                startActivity(i);
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                               waitingDialog.dismiss();
                                                Toast.makeText(AddNursingActivity.this, "Data Not Inserted Users Collection", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        waitingDialog.dismiss();
                                        Toast.makeText(AddNursingActivity.this, "Data Not Inserted in Doctor Collection", Toast.LENGTH_SHORT).show();

                                    }
                                });


                            }
                            else
                            {
                                Toast.makeText(AddNursingActivity.this, "Password Dissmatch", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(AddNursingActivity.this, "Passwords Must be Greater then 5 character", Toast.LENGTH_SHORT).show();
                        }

                    }


                }


            }
        });

    }
}
