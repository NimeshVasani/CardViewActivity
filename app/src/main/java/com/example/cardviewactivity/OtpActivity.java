package com.example.cardviewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class OtpActivity extends AppCompatActivity {

    private EditText mPhoneText;
    private EditText mCodeText;
    private TextView mErrorText;
    private Button mSendBtn;
    private FirebaseAuth mAuth;
    private int btnType=0;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    AlertDialog waitingDialog;
    DatabaseReference reference;
    FirebaseFirestore firestore;
    String catagory;
    String sname=LoginActivity.getname;
    String number=LoginActivity.getnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mPhoneText= findViewById(R.id.reg_phone);
        mCodeText= findViewById(R.id.reg_otp);
        mSendBtn= findViewById(R.id.reg_phone_btn);
        mErrorText=findViewById(R.id.errorText);
        mAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        waitingDialog=new AlertDialog.Builder(this).setMessage("Verifying...").setCancelable(true).create();
        catagory=getIntent().getStringExtra("catagory");
        mCodeText.setVisibility(View.INVISIBLE);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number1=mPhoneText.getText().toString().trim();

                if (number1.equals(number))
                {
                    waitingDialog.show();
                    if (btnType == 0) {

                        String phoneNumber = "+1" + number1;
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                OtpActivity.this,
                                mCallbacks

                        );
                    } else {
                        String verificationCode = mCodeText.getText().toString();
                        PhoneAuthCredential
                                credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                        signInWithPhoneAuthCredential(credential);
                    }
                }
                else
                {
                    Toast.makeText(OtpActivity.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                waitingDialog.dismiss();
                mErrorText.setText("there was some error in login in.");
                mErrorText.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                btnType=1;
                waitingDialog.dismiss();
                mCodeText.setVisibility(View.VISIBLE);

                mPhoneText.setVisibility(View.INVISIBLE);
                mSendBtn.setText("Verify code");
                mSendBtn.setEnabled(true);
            }
        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser=mAuth.getCurrentUser();
                        assert firebaseUser != null;
                        final String userid=firebaseUser.getUid();


                        if(catagory.equals("Patient"))
                        {

                            reference= FirebaseDatabase.getInstance().getReference("Patient").child(userid);
                            HashMap<String,String> hashMap1=new HashMap<>();
                            hashMap1.put("id",userid);
                            hashMap1.put("username",sname);
                            hashMap1.put("image","default");
                            reference.setValue(hashMap1).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful())
                                {

                                    HashMap<String,String> hashMap11 =new HashMap<>();

                                    hashMap11.put("Current_Id",userid);
                                    hashMap11.put("Catagory",catagory);

                                    firestore.collection("CurrentUser").document(userid).set(hashMap11).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            waitingDialog.dismiss();
                                            Intent i=new Intent(OtpActivity.this,MiddleActivity.class);
                                            waitingDialog.show();
                                            startActivity(i);
                                            finish();

                                        }
                                    }).addOnFailureListener(e -> Toast.makeText(OtpActivity.this, "CurrentUser Collection Failed", Toast.LENGTH_SHORT).show());


                                }
                            }).addOnFailureListener(e -> Toast.makeText(OtpActivity.this, "Failed....", Toast.LENGTH_SHORT).show());
                        }
                        else if(catagory.equals("Doctor"))
                        {


                            reference= FirebaseDatabase.getInstance().getReference("Doctor").child(userid);
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",sname);
                            hashMap.put("image","default");
                            reference.setValue(hashMap).addOnCompleteListener(task12 -> {
                                if(task12.isSuccessful())
                                {

                                    HashMap<String,String> hashMap1=new HashMap<>();

                                    hashMap1.put("Current_Id",userid);
                                    hashMap1.put("Catagory",catagory);

                                    firestore.collection("CurrentUser").document(userid).set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            waitingDialog.dismiss();
                                            Intent i=new Intent(OtpActivity.this,MiddleActivity.class);
                                            startActivity(i);
                                            finish();

                                        }
                                    }).addOnFailureListener(e -> Toast.makeText(OtpActivity.this, "CurrentUser Collection Failed", Toast.LENGTH_SHORT).show());
                                }
                            }).addOnFailureListener(e -> Toast.makeText(OtpActivity.this, "Failed....", Toast.LENGTH_SHORT).show());


                        }

                        else if(catagory.equals("Nursing"))
                        {
                            HashMap<String,String> hashMap1=new HashMap<>();

                            hashMap1.put("Current_Id",userid);
                            hashMap1.put("Catagory",catagory);

                            firestore.collection("CurrentUser").document(userid).set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    waitingDialog.dismiss();
                                    Intent i=new Intent(OtpActivity.this,MiddleActivity.class);
                                    startActivity(i);
                                    finish();

                                }
                            }).addOnFailureListener(e -> Toast.makeText(OtpActivity.this, "CurrentUser Collection Failed", Toast.LENGTH_SHORT).show());
                        }
                        else
                        {
                            waitingDialog.dismiss();
                            Toast.makeText(OtpActivity.this, "User Not Valid", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        mErrorText.setText("there was some error in loggin in.");
                        mErrorText.setVisibility(View.VISIBLE);
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                        }
                    }
                });
    }
}
