package com.example.salmanrameli.environmis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.salmanrameli.db.ReportContract;
import com.example.salmanrameli.db.ReportDbHelper;

import java.util.ArrayList;

public class IndexReport extends AppCompatActivity {
    private ReportDbHelper reportDbHelper;
    private ListView measurementResultListView;
    private ArrayAdapter<String> listArrayAdapter;
    CustomCursorAdapter customCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_report);

        reportDbHelper = new ReportDbHelper(this);
        measurementResultListView = (ListView) findViewById(R.id.measurementList);

        ArrayList<String> measurementList = new ArrayList<>();

        SQLiteDatabase db = reportDbHelper.getReadableDatabase();

        Cursor cursor = db.query(ReportContract.ReportResultEntry.TABLE,
                new String[]{ReportContract.ReportResultEntry._ID, ReportContract.ReportResultEntry.COL_LOCATION_NAME, ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE},
                null, null, null, null, null);

        while(cursor.moveToNext())
        {
            int location_index = cursor.getColumnIndex(ReportContract.ReportResultEntry.COL_LOCATION_NAME);
            int date = cursor.getColumnIndex(ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE);

            measurementList.add(cursor.getString(location_index));
            measurementList.add(cursor.getString(date));
        }

//        === LAYOUT LAYAR METODE SIMPLE CURSOR ADAPTER ===
//        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.layout_report_list, cursor, new String[]{ ReportContract.ReportResultEntry.COL_LOCATION_NAME, ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE }, new int[]{ R.id.report_location, R.id.report_date }, 0);
//        measurementResultListView.setAdapter(simpleCursorAdapter);
//        === AKHIR LAYOUT LAYAR METODE SIMPLE CURSOR ADAPTER ===

        customCursorAdapter = new CustomCursorAdapter(this, cursor);
        measurementResultListView.setAdapter(customCursorAdapter);

        db.close();
    }

}
