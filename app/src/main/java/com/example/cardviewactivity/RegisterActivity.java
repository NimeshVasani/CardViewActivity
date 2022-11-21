package com.example.cardviewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {


    MaterialEditText name,address,age,number,email,password,repassword;
    RadioButton male,female;
    Button next,login;

    FirebaseFirestore firestore;
    String gender;

    AlertDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        number=findViewById(R.id.number);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);

        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        next=findViewById(R.id.next);
        login=findViewById(R.id.login);

        waitingDialog=new AlertDialog.Builder(this).setMessage("Registering...").setCancelable(true).create();

        firestore=FirebaseFirestore.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sname=name.getText().toString().trim();
                String saddress=address.getText().toString().trim();
                String sage=age.getText().toString().trim();
                final String snumber=number.getText().toString().trim();
                final String semail=email.getText().toString().trim();
                final String spassword=password.getText().toString().trim();
                String srepassword=repassword.getText().toString().trim();



                if(sname.isEmpty())
                {
                    name.setError("Enter Your Name");
                    name.setErrorColor(R.color.colorPrimaryDark);

                }
                 else if(saddress.isEmpty())
                {
                    address.setError("Enter Your Address");
                    address.setErrorColor(R.color.error);
                }
               else if(sage.isEmpty())
                {
                    age.setError("Enter Your Age");
                    age.setErrorColor(R.color.error);
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
                    Toast.makeText(RegisterActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
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
                    if(spassword.length()>=6) {
                        if (spassword.equals(srepassword)) {

                            waitingDialog.show();

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("Name", sname);
                            hashMap.put("Address", saddress);
                            hashMap.put("Number", snumber);
                            hashMap.put("Age", sage);
                            hashMap.put("Email", semail);
                            hashMap.put("Password", spassword);
                            hashMap.put("Gender", gender);

                            firestore.collection("Patient").document(semail).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    HashMap<String, Object> hash = new HashMap<>();
                                    hash.put("Number", snumber);
                                    hash.put("Email", semail);
                                    hash.put("Password", spassword);
                                    hash.put("Catagory", "Patient");
                                    hash.put("Name",sname);

                                    firestore.collection("Users").document(semail).set(hash).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {


                                               waitingDialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                                               Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                                               startActivity(i);

                                        }
                                    });


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    waitingDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Password Dissmatch", Toast.LENGTH_SHORT).show();
                        }
                    }
                   else
                   {
                       Toast.makeText(RegisterActivity.this, "Password Must Be More Than five Character", Toast.LENGTH_SHORT).show();
                   }
                }



            }
        });
    }
}
