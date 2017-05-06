package com.example.salmanrameli.environmis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeValidatorStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_validator_staff);
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
        SharedPreferences sharedPreferences = getSharedPreferences(SignIn.MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        Intent intent = new Intent(HomeValidatorStaff.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
