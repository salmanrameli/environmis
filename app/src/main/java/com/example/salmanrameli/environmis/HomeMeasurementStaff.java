package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeMeasurementStaff extends AppCompatActivity {
    String _id;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public void createReport(View view)
    {
        Intent intent = new Intent(this, CreateReport.class);

        intent.putExtra("user_id_session", _id);

        startActivity(intent);
    }

    public void measurementCheckTaskButtonOnClick(View view) {
        Intent intent = new Intent(this, MeasurementCheckTask.class);

        intent.putExtra("user_id_session", _id);

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
