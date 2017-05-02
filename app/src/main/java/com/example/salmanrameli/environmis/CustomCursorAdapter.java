package com.example.salmanrameli.environmis;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    public void bindView(View view, final Context context, final Cursor cursor) {
        ReportDbHelper reportDbHelper = new ReportDbHelper(context);
        final SQLiteDatabase db = reportDbHelper.getWritableDatabase();

        Button deleteReportButton = (Button) view.findViewById(R.id.deleteMeasurementbutton);
        Button viewDetailsButton = (Button) view.findViewById(R.id.viewDetailsButton);

        TextView location_latitude_text = (TextView) view.findViewById(R.id.list_report_location_latitude_);
        TextView location_longitude_text = (TextView) view.findViewById(R.id.list_report_location_longitude_);
        TextView date_text = (TextView) view.findViewById(R.id.report_date);

        location_latitude_text.setText(cursor.getString(cursor.getColumnIndex("report_location_latitude")));
        location_longitude_text.setText(cursor.getString(cursor.getColumnIndex("report_location_longitude")));
        date_text.setText(cursor.getString(cursor.getColumnIndex("report_date")));

        final long _id = cursor.getLong(cursor.getColumnIndex("_id"));

        deleteReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "delete " + _id);

                db.delete(ReportContract.ReportResultEntry.TABLE,
                        ReportContract.ReportResultEntry._ID + " = ?", new String[]{String.valueOf(_id)});

                Cursor cursor = db.query(ReportContract.ReportResultEntry.TABLE,
                        new String[]{ReportContract.ReportResultEntry._ID, ReportContract.ReportResultEntry.COL_LOCATION_LATITUDE, ReportContract.ReportResultEntry.COL_LOCATION_LONGITUDE, ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE},
                        null, null, null, null, null);

                notifyDataSetChanged();

                Toast.makeText(context.getApplicationContext(), "Report deleted", Toast.LENGTH_LONG).show();

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
                    String location_latitude_report = cursor.getString(1);
                    String location_longitude_report = cursor.getString(2);
                    String date_report = cursor.getString(3);
                    String result_report = cursor.getString(4);

                    Log.d(TAG, "location_latitude: " + location_latitude_report);
                    Log.d(TAG, "location_longitude: " + location_longitude_report);
                    Log.d(TAG, "date: " + date_report);
                    Log.d(TAG, "result: " + result_report);
                    cursor.close();

                    Intent intent = new Intent(context, ShowReport.class);

                    intent.putExtra("location_latitude", location_latitude_report);
                    intent.putExtra("location_longitude", location_longitude_report);
                    intent.putExtra("date", date_report);
                    intent.putExtra("result", result_report);

                    context.startActivity(intent);
                }
            }
        });
    }
}
