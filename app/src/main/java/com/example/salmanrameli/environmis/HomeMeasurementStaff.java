package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeMeasurementStaff extends AppCompatActivity {
    String _id;
    String name;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        _id = firebaseUser.getUid();

        databaseReference.child(FirebaseStrings.USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = (String) dataSnapshot.child(_id).child(FirebaseStrings.USERNAME).getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void createReport(View view)
    {
        Intent intent = new Intent(this, CreateReport.class);

        intent.putExtra("user_id_session", _id);

        startActivity(intent);
    }

    public void measurementCheckTaskButtonOnClick(View view) {
        Intent intent = new Intent(this, MeasurementCheckTask.class);

        intent.putExtra(FirebaseStrings.USERNAME, name);

        startActivity(intent);
    }

    public void signOutButtonOnClick(View view)
    {
        firebaseAuth.signOut();

        Intent intent = new Intent(HomeMeasurementStaff.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
