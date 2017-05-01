package com.example.salmanrameli.environmis;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salmanrameli.db.ReportContract;
import com.example.salmanrameli.db.ReportDbHelper;

import java.util.Locale;

public class CreateReport extends AppCompatActivity {
    private ReportDbHelper reportDbHelper;
    EditText report_location_text;
    EditText report_date_text;
    EditText report_result_text;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        reportDbHelper = new ReportDbHelper(this);

        report_location_text = (EditText) findViewById(R.id.report_location_);
        report_date_text = (EditText) findViewById(R.id.report_date_);
        report_result_text = (EditText) findViewById(R.id.report_result_);

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
        String location_name = String.valueOf(report_location_text.getText());
        String measurement_date = String.valueOf(report_date_text.getText());
        String measurement_result = String.valueOf(report_result_text.getText());

        SQLiteDatabase db = reportDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ReportContract.ReportResultEntry.COL_LOCATION_NAME, location_name);
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
