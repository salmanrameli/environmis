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

        TextView locationLatitudeTextView = (TextView) findViewById(R.id.textViewLocationLatitude);
        TextView locationLongitudeTextView = (TextView) findViewById(R.id.textViewLocationLongitude);
        TextView dateTextView = (TextView) findViewById(R.id.textViewDate);
        TextView resultTextView = (TextView) findViewById(R.id.textViewResult);

        Intent intent = getIntent();

        String location_latitude = intent.getStringExtra("location_latitude");
        String location_longitude = intent.getStringExtra("location_longitude");
        String date = intent.getStringExtra("date");
        String result = intent.getStringExtra("result");

        locationLatitudeTextView.setText("Location latitude:\n" + location_latitude);
        locationLongitudeTextView.setText("Location longitude:\n" + location_longitude);
        dateTextView.setText("Measurement Date:\n" + date);
        resultTextView.setText("Measurement Result:\n" + result);
    }
}
