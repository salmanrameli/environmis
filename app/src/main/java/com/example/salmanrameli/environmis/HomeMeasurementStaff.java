package com.example.salmanrameli.environmis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeMeasurementStaff extends AppCompatActivity {
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        _id = getIntent().getStringExtra("user_id_session");
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
        SharedPreferences sharedPreferences = getSharedPreferences(SignIn.MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        Intent intent = new Intent(HomeMeasurementStaff.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
