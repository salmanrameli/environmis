package com.example.salmanrameli.environmis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.salmanrameli.db.ReportContract;
import com.example.salmanrameli.db.ReportDbHelper;

/**
 * Created by SalmanRameli on 4/30/17.
 */

class CustomCursorAdapter extends CursorAdapter {
    private String TAG = "CustomCursorAdapter";

    CustomCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_report_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        ReportDbHelper reportDbHelper = new ReportDbHelper(context);
        final SQLiteDatabase db = reportDbHelper.getWritableDatabase();

        Button deleteReportButton = (Button) view.findViewById(R.id.deleteMeasurementbutton);
        Button viewDetailsButton = (Button) view.findViewById(R.id.viewDetailsButton);

        TextView location_text = (TextView) view.findViewById(R.id.report_location);
        TextView date_text = (TextView) view.findViewById(R.id.report_date);

        location_text.setText(cursor.getString(cursor.getColumnIndex("report_location")));
        date_text.setText(cursor.getString(cursor.getColumnIndex("report_date")));

        final long _id = cursor.getLong(cursor.getColumnIndex("_id"));

        deleteReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "delete " + _id);

                db.delete(ReportContract.ReportResultEntry.TABLE,
                        ReportContract.ReportResultEntry._ID + " = ?", new String[]{String.valueOf(_id)});

                Cursor cursor = db.query(ReportContract.ReportResultEntry.TABLE,
                        new String[]{ReportContract.ReportResultEntry._ID, ReportContract.ReportResultEntry.COL_LOCATION_NAME, ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE},
                        null, null, null, null, null);


                notifyDataSetChanged();

                changeCursor(cursor);
            }
        });

        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "_id: " + String.valueOf(_id));

                Cursor cursor = db.query(ReportContract.ReportResultEntry.TABLE,
                        null, ReportContract.ReportResultEntry._ID + " = ?", new String[]{String.valueOf(_id)},
                        null, null, null);

                if(cursor != null && cursor.moveToFirst())
                {
                    String location_report = cursor.getString(1);
                    String date_report = cursor.getString(2);
                    String result_report = cursor.getString(3);

                    Log.d(TAG, "location: " + location_report);
                    Log.d(TAG, "date: " + date_report);
                    Log.d(TAG, "result: " + result_report);
                    cursor.close();
                }
            }
        });
    }
}
