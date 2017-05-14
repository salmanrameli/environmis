package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class HomeValidatorStaff extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_validator_staff);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public void viewReportButtonOnClick(View view)
    {
        Intent intent = new Intent(this, IndexReport.class);

        startActivity(intent);
    }

    public void assignTaskButtonOnClick(View view)
    {
        Intent intent = new Intent(this, AssignTask.class);

        startActivity(intent);
    }

    public void validatorSignOutButtonOnClick(View view)
    {
        firebaseAuth.signOut();

        Intent intent = new Intent(HomeValidatorStaff.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
