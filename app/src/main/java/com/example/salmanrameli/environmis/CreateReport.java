package com.example.salmanrameli.environmis;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.salmanrameli.db.ReportContract;
import com.example.salmanrameli.db.ReportDbHelper;

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

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month +1, day);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        report_date_text.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

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

        finish();
    }
}
