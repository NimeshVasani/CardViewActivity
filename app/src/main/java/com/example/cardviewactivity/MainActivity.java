package com.example.cardviewactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardviewactivity.DoctorVisit.DoctorVisit;
import com.example.cardviewactivity.DoctorVisit.HomeServiceActivity;
import com.example.cardviewactivity.Medical.MedicalProblems;
import com.example.cardviewactivity.NearbyLocations.GMap.ListHealthCenters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    androidx.cardview.widget.CardView c, healthcare, findhospital, doctorvisit, homeservice, findmedicine, findmedical, emergency;

    androidx.appcompat.widget.Toolbar toolbar;

    ProgressDialog progressDoalog;


    DatabaseReference reference, reference1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findmedical = findViewById(R.id.findmedical);
        c = findViewById(R.id.consultancy);
        findmedicine = findViewById(R.id.findmedicine);
        doctorvisit = findViewById(R.id.doctorvisit);
        homeservice = findViewById(R.id.homeservice);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.item);
        emergency = findViewById(R.id.emergency);
        healthcare = findViewById(R.id.healthcare);

        reference = FirebaseDatabase.getInstance().getReference("Friend Request").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String abc = dataSnapshot.getValue().toString();

                    Toast.makeText(MainActivity.this, abc, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        healthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, HealthcareActivity.class);
                startActivity(i);

            }
        });

        findhospital = findViewById(R.id.findhospital);
        findmedical.setOnClickListener(v -> hospitalLocations());
        findmedicine.setOnClickListener(v -> {
            medicalProblems();
        });
        doctorvisit.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DoctorVisit.class);
            startActivity(i);
        });
        homeservice.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, HomeServiceActivity.class);
            startActivity(i);
        });
        emergency.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, EmergencyActivity.class);
            startActivity(i);

        });

        findhospital.setOnClickListener(v -> hospitalLocations());

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            return false;
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        c.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DiseaseListActivity.class);
            startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.item, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }
        if (item.getItemId() == R.id.profile) {
            Toast.makeText(MainActivity.this, "Click....", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    void medicalProblems() {
        loading("Loading...");
        Intent intent = new Intent(this, MedicalProblems.class);
        startActivity(intent);
    }

    void hospitalLocations() {
        if (isNetworkAvailable()) {
            loading("Scanning Location...");
            Intent intent = new Intent(this, ListHealthCenters.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
    }

    /**
     * isNetworkAvailable
     *
     * @return true if internet connection is available and @return false if not available
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * show progress dialog while loading maps or problems
     */
    void loading(String message) {
        Medikit.progressDialog = new ProgressDialog(this);
        Medikit.progressDialog.setTitle("Please wait");
        Medikit.progressDialog.setMessage(message);
        Medikit.progressDialog.setCancelable(false);
        Medikit.progressDialog.show();
    }
}
