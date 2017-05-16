package com.example.salmanrameli.environmis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IndexReport extends AppCompatActivity {
    private FirebaseCustomAdapter firebaseCustomAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    ArrayList<Reports> measurementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_report);

        final ListView measurementResultListView = (ListView) findViewById(R.id.measurementList);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("reports").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Reports reports = snapshot.getValue(Reports.class);

                    measurementList.add(reports);

                    Log.d("reports", "staff_id: " + reports.getStaff_id());
                    Log.d("reports", "latitude: " + reports.getLocation_latitude());
                    Log.d("reports", "longitude: " + reports.getLocation_longitude());
                    Log.d("reports", "date: " + reports.getMeasurement_date());
                    Log.d("reports", "result: " + reports.getMeasurement_result());
                }
                firebaseCustomAdapter = new FirebaseCustomAdapter(IndexReport.this, measurementList);
                measurementResultListView.setAdapter(firebaseCustomAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
