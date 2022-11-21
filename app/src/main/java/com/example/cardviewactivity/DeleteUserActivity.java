package com.example.cardviewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;


public class DeleteUserActivity extends AppCompatActivity {

    MaterialEditText email;
    Button delete;

    FirebaseFirestore firestore;

    AlertDialog waitingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        email=findViewById(R.id.email);
        delete=findViewById(R.id.delete);

        firestore=FirebaseFirestore.getInstance();

        waitingDialog=new AlertDialog.Builder(this).setMessage("Deleting...").setCancelable(true).create();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waitingDialog.show();

                String semail=email.getText().toString().trim();


                firestore.collection("Users").document(semail).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        waitingDialog.dismiss();
                        Toast.makeText(DeleteUserActivity.this, "Delete User Successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                       waitingDialog.dismiss();
                        Toast.makeText(DeleteUserActivity.this, "Deleting User Failed", Toast.LENGTH_SHORT).show();

                    }
                });

                /*firestore.collection("Users").document(semail).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists())
                        {
                            String getEmail=documentSnapshot.getString("Email");


                        }


                    }
                })
*/

            }
        });
    }
}
