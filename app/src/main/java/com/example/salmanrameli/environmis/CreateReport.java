package com.example.salmanrameli.environmis;

import android.app.DatePickerDialog;
import android.Manifest;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.salmanrameli.db.ReportContract;
import com.example.salmanrameli.db.ReportDbHelper;

import java.util.Locale;

public class CreateReport extends AppCompatActivity {
    private ReportDbHelper reportDbHelper;
    EditText report_location_latitude_text;
    EditText report_location_longitude_text;
    EditText report_date_text;
    EditText report_result_text;
    Calendar calendar = Calendar.getInstance();
    double latitude_val;
    double longitude_val;

    Button getLocationButton;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String permission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        report_location_latitude_text = (EditText) findViewById(R.id.report_location_latitude);
        report_location_longitude_text = (EditText) findViewById(R.id.report_location_longitude);
        report_date_text = (EditText) findViewById(R.id.report_date_);
        report_result_text = (EditText) findViewById(R.id.report_result_);

        try {
            if(ActivityCompat.checkSelfPermission(this, permission)
                    != MockPackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getLocationButton = (Button) findViewById(R.id.getLocationButton);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(CreateReport.this);

                if(gps.canGetLocation()) {
                    latitude_val = gps.getLatitude();
                    longitude_val = gps.getLongitude();

                    report_location_latitude_text.setText(String.valueOf(latitude_val));
                    report_location_longitude_text.setText(String.valueOf(longitude_val));

//                    Toast.makeText(getApplicationContext(), "Latitude: " + latitude_val + "\nLongitude: " + longitude_val, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        reportDbHelper = new ReportDbHelper(this);

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
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        report_date_text.setText(sdf.format(calendar.getTime()));
    };

    public void submitReportButtonOnClick(View view)
    {
        String location_latitude = String.valueOf(report_location_latitude_text.getText());
        String location_longitude = String.valueOf(report_location_longitude_text.getText());
        String measurement_date = String.valueOf(report_date_text.getText());
        String measurement_result = String.valueOf(report_result_text.getText());

        SQLiteDatabase db = reportDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ReportContract.ReportResultEntry.COL_LOCATION_LATITUDE, location_latitude);
        values.put(ReportContract.ReportResultEntry.COL_LOCATION_LONGITUDE, location_longitude);
        values.put(ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE, measurement_date);
        values.put(ReportContract.ReportResultEntry.COL_MEASUREMENT_RESULT, measurement_result);

        db.insertWithOnConflict(ReportContract.ReportResultEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();

        Toast.makeText(this, "Report submitted", Toast.LENGTH_LONG).show();

        finish();
    }
}
