package com.example.cardviewactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardviewactivity.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PatientRequestActivity extends AppCompatActivity {
    private String receiverUserId="",receiverUserName="";
    private TextView name_profile;
    private Button add_friend,decline_friend_request;
    private FirebaseAuth mAuth;
    private String senderUserId;
    private String currentState="new";
    String patientName,doctorName;
    private DatabaseReference friendRequestRef,contactsRef,reference,reference2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request);
        name_profile=findViewById(R.id.name_profile);
        add_friend=findViewById(R.id.add_friend);
        mAuth=FirebaseAuth.getInstance();
        senderUserId=mAuth.getCurrentUser().getUid();

        friendRequestRef= FirebaseDatabase.getInstance().getReference().child("Friend Request");
        contactsRef= FirebaseDatabase.getInstance().getReference().child("Contacts");
        decline_friend_request=findViewById(R.id.decline_friend_request);
        receiverUserId=getIntent().getExtras().get("visit_user_id").toString();
        receiverUserName=getIntent().getExtras().get("profile_name").toString();
        name_profile.setText(receiverUserName);
        reference= FirebaseDatabase.getInstance().getReference().child("Patient").child(receiverUserId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    User user=dataSnapshot.getValue(User.class);
                    patientName=user.getUsername();
                    Toast.makeText(PatientRequestActivity.this,patientName, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference2= FirebaseDatabase.getInstance().getReference().child("Doctor").child(senderUserId);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    User user=dataSnapshot.getValue(User.class);
                    doctorName=user.getUsername();
                    Toast.makeText(PatientRequestActivity.this,doctorName, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        manageClickEvents();
    }


    private void manageClickEvents()
    {
        friendRequestRef.child(senderUserId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(receiverUserId))
                        {
                            String requestType=dataSnapshot.child(receiverUserId).child("request_type").getValue().toString();
                            if(requestType.equals("sent"))
                            {
                                currentState="request_sent";
                                add_friend.setText("Cancel Request");
                            }
                            else if(requestType.equals("received"))
                            {
                                currentState="request_received";
                                add_friend.setText("Accept Request");
                                decline_friend_request.setVisibility(View.VISIBLE);
                                decline_friend_request.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CancelFriendRequest();
                                    }
                                });
                            }
                        }
                        else
                        {
                            contactsRef.child(senderUserId)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.hasChild(receiverUserId))
                                            {
                                                Intent i=new Intent(PatientRequestActivity.this,MessageActivity.class);
                                                i.putExtra("userid",receiverUserId);
                                                startActivity(i);
                                                currentState="friends";
                                                add_friend.setText("Delete Contact");
                                            }
                                            else
                                            {
                                                currentState="new";
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        if(senderUserId.equals(receiverUserId))
        {
            add_friend.setVisibility(View.GONE);
        }
        else
        {
            add_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentState.equals("new"))
                    {
                        SendFriendRequest();
                    }
                    if(currentState.equals("request_sent"))
                    {
                        CancelFriendRequest();
                    }
                    if(currentState.equals("request_received"))
                    {
                        AcceptFriendRequest();
                    }
                    if(currentState.equals("request_sent"))
                    {
                        CancelFriendRequest();
                    }
                }
            });
        }
        /*refresh(3000);*/
    }

   /* private void refresh(int milliseconds) {
        final Handler handler=new Handler();
        final  Runnable runnable=new Runnable() {
            @Override
            public void run() {
                manageClickEvents();
            }
        };
        handler.postDelayed(runnable,milliseconds);

    }*/

    private void AcceptFriendRequest()
    {

        HashMap<String,Object> hashMap2=new HashMap<>();
        hashMap2.put("id",receiverUserId);
        hashMap2.put("Contact","Saved");
        hashMap2.put("username",patientName);
        contactsRef.child(senderUserId).child(receiverUserId)
                .setValue(hashMap2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            HashMap<String,Object> hashMap1=new HashMap<>();
                            hashMap1.put("id",senderUserId);
                            hashMap1.put("Contact","Saved");
                            hashMap1.put("username",doctorName);
                            contactsRef.child(receiverUserId).child(senderUserId)
                                    .setValue(hashMap1)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                                friendRequestRef.child(senderUserId).child(receiverUserId)
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful())
                                                                {
                                                                    friendRequestRef.child(receiverUserId).child(senderUserId)
                                                                            .removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if(task.isSuccessful())
                                                                                    {
                                                                                        Intent i=new Intent(PatientRequestActivity.this,PatientList2Activity.class);
                                                                                        i.putExtra("userid",receiverUserId);
                                                                                        startActivity(i);
                                                                                        currentState="friends";
                                                                                        add_friend.setText("Delete Contact");
                                                                                        decline_friend_request.setVisibility(View.GONE);
                                                                                    }
                                                                                }
                                                                            });
                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    });
                        }
                    }
                });

    }

    private void CancelFriendRequest()
    {
        friendRequestRef.child(senderUserId).child(receiverUserId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            friendRequestRef.child(receiverUserId).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                currentState="new";
                                                add_friend.setText("Send Request");
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private void SendFriendRequest()
    {
        friendRequestRef.child(senderUserId).child(receiverUserId)
                .child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("id",senderUserId);
                            hashMap.put("request_type","received");
                            hashMap.put("username",patientName);
                            friendRequestRef.child(receiverUserId).child(senderUserId)
                                    .setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                                currentState="request_sent";
                                                add_friend.setText("Cancel Request");
                                                Toast.makeText(PatientRequestActivity.this, "Friend Request Sent.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}
