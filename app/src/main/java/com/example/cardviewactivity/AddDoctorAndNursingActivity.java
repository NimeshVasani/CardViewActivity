package com.example.cardviewactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;


public class AddDoctorAndNursingActivity extends AppCompatActivity {

    MaterialEditText name, workplace, age, degree, expirience, number, email, password, repassword;
    Spinner sp;
    RadioButton male, female;
    Button submit;
    String gender;
    String expertization;
    String d[] = {"Select Expertization", "Women's issues", "General Physician", "Sexual Problems", "Stress and Mental Health", "Skin Problems", "Hair and Scalp Problems", "Pregnancy Problems", "Conceiving issues", "Child/Infant issues", "Acidity,Gas,Stomach issues", "Neurology", "Orthopedics-Bones,Joints issues", "Cancer Advice", "Diabetes Consult", "Cardiac issue-Heart related issues"};
    AlertDialog waitingDialog;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_and_nursing);

        name = findViewById(R.id.name);
        workplace = findViewById(R.id.workplace);
        age = findViewById(R.id.age);
        degree = findViewById(R.id.degree);
        sp = findViewById(R.id.expertization);
        expirience = findViewById(R.id.experience);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        submit = findViewById(R.id.submit);
        waitingDialog = new AlertDialog.Builder(this).setMessage("Adding Doctor...").setCancelable(true).create();
        ArrayAdapter<String> ad = new ArrayAdapter<String>(AddDoctorAndNursingActivity.this, R.layout.support_simple_spinner_dropdown_item, d);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(AddDoctorAndNursingActivity.this, "Please Select Expertization...", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    expertization = "Women's issues";
                }
                if (position == 2) {
                    expertization = "General Physician";
                }
                if (position == 3) {
                    expertization = "Sexual Problems";
                }
                if (position == 4) {
                    expertization = "Stress and Mental Health";
                }
                if (position == 5) {
                    expertization = "Skin Problems";
                }
                if (position == 6) {
                    expertization = "Hair and Scalp Problems";
                }
                if (position == 7) {
                    expertization = "Pregnancy Problems";
                }
                if (position == 8) {
                    expertization = "Conceiving issues";
                }
                if (position == 9) {
                    expertization = "Child/Infant issues";
                }
                if (position == 10) {
                    expertization = "Acidity,Gas,Stomach issues";
                }
                if (position == 11) {
                    expertization = "Neurology";
                }
                if (position == 12) {
                    expertization = "Orthopedics-Bones,Joints issues";
                }
                if (position == 13) {
                    expertization = "Cancer Advice";
                }
                if (position == 14) {
                    expertization = "Diabetes Consult";
                }
                if (position == 15) {
                    expertization = "Cardiac issue-Heart related issues";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        firestore = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sname = name.getText().toString().trim();
                String sworkplace = workplace.getText().toString().trim();
                String sage = age.getText().toString().trim();
                String sdegree = degree.getText().toString().trim();
                String sexpertization = expertization.trim();
                String sexpirience = expirience.getText().toString().trim();
                final String snumber = number.getText().toString().trim();
                final String semail = email.getText().toString().trim();
                final String spassword = password.getText().toString().trim();
                String srepassword = repassword.getText().toString().trim();


                if (sname.isEmpty()) {
                    name.setError("Enter Your Name");
                    name.setErrorColor(R.color.colorPrimaryDark);

                } else if (sworkplace.isEmpty()) {
                    workplace.setError("Enter Work Place");
                    workplace.setErrorColor(R.color.error);
                } else if (sage.isEmpty()) {
                    age.setError("Enter Your Age");
                    age.setErrorColor(R.color.error);
                } else if (sdegree.isEmpty()) {
                    degree.setError("Enter Degree");
                    degree.setErrorColor(R.color.error);
                } else if (sexpertization.isEmpty()) {
                    degree.setError("Enter Expertization");
                    degree.setErrorColor(R.color.error);
                } else if (sexpirience.isEmpty()) {
                    degree.setError("Enter Expirience");
                    degree.setErrorColor(R.color.error);
                } else if (snumber.isEmpty()) {
                    number.setError("Enter Your ");
                    number.setErrorColor(R.color.error);
                } else if (semail.isEmpty()) {
                    email.setError("Enter Your Email");
                    email.setErrorColor(R.color.error);
                } else if (spassword.isEmpty()) {
                    password.setError("Enter Your Password ");
                    password.setErrorColor(R.color.error);
                } else if (srepassword.isEmpty()) {
                    repassword.setError("Enter Your Re-password ");
                    repassword.setErrorColor(R.color.error);
                } else if (!male.isChecked() && !female.isChecked()) {
                    Toast.makeText(AddDoctorAndNursingActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
                } else {

                    if (male.isChecked()) {
                        gender = "male";
                    } else {
                        gender = "female";

                    }
                    if (spassword.length() >= 6) {
                        if (spassword.equals(srepassword)) {

                            waitingDialog.show();

                            final HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("Name", sname);
                            hashMap.put("Work Place", sworkplace);
                            hashMap.put("Age", sage);
                            hashMap.put("Degree", sdegree);
                            hashMap.put("Expertization", sexpertization);
                            hashMap.put("Expirience", sexpirience);
                            hashMap.put("Number", snumber);
                            hashMap.put("Email", semail);
                            hashMap.put("Password", spassword);
                            hashMap.put("Gender", gender);


                            firestore.collection("Doctor").document(semail).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    HashMap<String, Object> hashMap1 = new HashMap<>();
                                    hashMap1.put("Catagory", "Doctor");
                                    hashMap1.put("Email", semail);
                                    hashMap1.put("Number", snumber);
                                    hashMap1.put("Expertization", sexpertization);
                                    hashMap1.put("Password", spassword);
                                    hashMap1.put("Name", sname);
                                    Toast.makeText(getApplicationContext(), sexpertization, Toast.LENGTH_SHORT).show();
                                    firestore.collection("Users").document(semail).set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            waitingDialog.show();
                                            Toast.makeText(AddDoctorAndNursingActivity.this, "Doctor add Successfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(AddDoctorAndNursingActivity.this, AdminActivity.class);
                                            startActivity(i);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            waitingDialog.dismiss();
                                            Toast.makeText(AddDoctorAndNursingActivity.this, "Data Not Inserted Users Collection", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    waitingDialog.dismiss();
                                    Toast.makeText(AddDoctorAndNursingActivity.this, "Data Not Inserted in Doctor Collection", Toast.LENGTH_SHORT).show();

                                }
                            });


                        } else {
                            Toast.makeText(AddDoctorAndNursingActivity.this, "Password Dissmatch", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(AddDoctorAndNursingActivity.this, "Passwords Must be Greater then 5 character", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

    }
}
