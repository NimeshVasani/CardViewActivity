package com.example.cardviewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;


public class LoginActivity extends AppCompatActivity {

    MaterialEditText email,password;
    Button signin,login;

    TextView forgete;

    FirebaseFirestore firestore,firestore1;
    FirebaseAuth auth;

    AlertDialog waitingDialog;

   static String getcatagory,getemail,getnumber,getpassword,getname,getexpertization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        signin=findViewById(R.id.singin);
        login=findViewById(R.id.login);

        firestore=FirebaseFirestore.getInstance();
        firestore1=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        forgete=findViewById(R.id.forgetepassword);

        waitingDialog=new AlertDialog.Builder(this).setMessage("Logging...").setCancelable(true).create();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String semail = email.getText().toString().trim();
                final String spassword = password.getText().toString().trim();
                if(semail.equals("myowndoctor3@gmail.com") && spassword.equals("984237")) {
                    Intent i=new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(i);
                    finish();

                }
                else{


                if (semail.isEmpty()) {
                    email.setError("Enter Your Email");
                    email.setErrorColor(R.color.colorPrimaryDark);

                } else if (spassword.isEmpty()) {
                    password.setError("Enter Your Address");
                    password.setErrorColor(R.color.error);
                } else {


                    waitingDialog.show();


                    firestore.collection("Users").document(semail).get().addOnSuccessListener(documentSnapshot -> {

                        if (documentSnapshot.exists()) {

                            getcatagory = documentSnapshot.getString("Catagory");
                            getemail = documentSnapshot.getString("Email");
                            getnumber = documentSnapshot.getString("Number");
                            getpassword = documentSnapshot.getString("Password");
                            getname=documentSnapshot.getString("Name");
                            getexpertization=documentSnapshot.getString("Expertization");

                            if (spassword.equals(getpassword)) {
                                waitingDialog.dismiss();
                                Intent i = new Intent(LoginActivity.this, OtpActivity.class);
                                i.putExtra("catagory",getcatagory);
                                i.putExtra("Expertization",getexpertization);
                                startActivity(i);



                            } else {
                               waitingDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                           waitingDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                        }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           waitingDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Data Retrieving Fail", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }


            }
        });
    }

    @Override
    protected void onStart() {

        FirebaseUser user=auth.getCurrentUser();

        if(user!=null)
        {
           Intent i=new Intent(LoginActivity.this,MiddleActivity.class);
           startActivity(i);
           finish();
        }
        super.onStart();
    }
}
