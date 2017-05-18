package com.example.salmanrameli.environmis;

import android.app.DatePickerDialog;
import android.Manifest;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateReport extends AppCompatActivity {
    GPSTracker gps;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Button submitButton;
    EditText report_location_latitude_text;
    EditText report_location_longitude_text;
    EditText report_date_text;
    EditText report_result_text;

    String _id;
    String todo_key;

    Calendar calendar = Calendar.getInstance();

    double latitude_val;
    double longitude_val;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String permission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        final Intent intent = getIntent();

        todo_key = intent.getStringExtra("todo_key");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        _id = firebaseUser.getUid();

        report_location_latitude_text = (EditText) findViewById(R.id.report_location_latitude);
        report_location_longitude_text = (EditText) findViewById(R.id.report_location_longitude);
        report_date_text = (EditText) findViewById(R.id.report_date_);
        report_result_text = (EditText) findViewById(R.id.report_result_);

        try {
            if(ActivityCompat.checkSelfPermission(this, permission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        gps = new GPSTracker(CreateReport.this);

        Toast.makeText(CreateReport.this, "Fetching current location coordinate", Toast.LENGTH_SHORT).show();

        if(gps.canGetLocation()) {
            latitude_val = gps.getLatitude();
            longitude_val = gps.getLongitude();

            report_location_latitude_text.setText("");
            report_location_longitude_text.setText("");

            report_location_latitude_text.setText(String.valueOf(latitude_val));
            report_location_longitude_text.setText(String.valueOf(longitude_val));

            Toast.makeText(CreateReport.this, "Current location fetched", Toast.LENGTH_SHORT).show();

        } else {
            gps.showSettingsAlert();
        }

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        report_date_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateReport.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location_latitude = String.valueOf(report_location_latitude_text.getText());
                String location_longitude = String.valueOf(report_location_longitude_text.getText());
                String measurement_date = String.valueOf(report_date_text.getText());
                String measurement_result = String.valueOf(report_result_text.getText());

                Map<String, String> map = new HashMap<String, String>();

                map.put("staff_id", _id);
                map.put("location_latitude", location_latitude);
                map.put("location_longitude", location_longitude);
                map.put("measurement_date", measurement_date);
                map.put("measurement_result", measurement_result);

                databaseReference.child("reports").push().setValue(map);

                databaseReference.child("todo").child(todo_key).child("is_done").setValue("true");

                Toast.makeText(CreateReport.this, "Report submitted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CreateReport.this, HomeMeasurementStaff.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        report_date_text.setText(sdf.format(calendar.getTime()));
    };
}
