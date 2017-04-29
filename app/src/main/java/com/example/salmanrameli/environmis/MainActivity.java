package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createReport(View view)
    {
        Intent intent = new Intent(this, CreateReport.class);

        startActivity(intent);
    }

    public void viewReportButtonOnClick(View view)
    {
        Intent intent = new Intent(this, IndexReport.class);

        startActivity(intent);
    }
}
