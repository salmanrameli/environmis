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

public class CustomCursorAdapter extends CursorAdapter {
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

        Button deleteButton = (Button) view.findViewById(R.id.deleteMeasurementbutton);

        TextView location_text = (TextView) view.findViewById(R.id.report_location);
        TextView date_text = (TextView) view.findViewById(R.id.report_date);

        location_text.setText(cursor.getString(cursor.getColumnIndex("report_location")));
        date_text.setText(cursor.getString(cursor.getColumnIndex("report_date")));

        final String location = cursor.getString(cursor.getColumnIndex("report_location"));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "delete " + location);

                db.delete(ReportContract.ReportResultEntry.TABLE,
                        ReportContract.ReportResultEntry.COL_LOCATION_NAME + " = ?", new String[]{location});

                Cursor cursor = db.query(ReportContract.ReportResultEntry.TABLE,
                        new String[]{ReportContract.ReportResultEntry._ID, ReportContract.ReportResultEntry.COL_LOCATION_NAME, ReportContract.ReportResultEntry.COL_MEASUREMENT_DATE},
                        null, null, null, null, null);


                notifyDataSetChanged();
                changeCursor(cursor);
            }
        });
    }
}
