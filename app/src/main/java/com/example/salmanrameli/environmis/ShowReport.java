package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);

        TextView locationTextView = (TextView) findViewById(R.id.textViewLocation);
        TextView dateTextView = (TextView) findViewById(R.id.textViewDate);
        TextView resultTextView = (TextView) findViewById(R.id.textViewResult);

        Intent intent = getIntent();

        String location = intent.getStringExtra("location");
        String date = intent.getStringExtra("date");
        String result = intent.getStringExtra("result");

        locationTextView.setText("Measurement Location:\n" + location);
        dateTextView.setText("Measurement Date:\n" + date);
        resultTextView.setText("Measurement Result:\n" + result);
    }
}
