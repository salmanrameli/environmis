package com.example.salmanrameli.environmis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.salmanrameli.db.AssignTaskDbHelper;

class MeasurementCheckTaskCursorAdapter extends CursorAdapter {
    MeasurementCheckTaskCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_measurement_check_task, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView location_latitude_text = (TextView) view.findViewById(R.id.checkTaskLocationLatitudeTextView);
        TextView location_longitude_text = (TextView) view.findViewById(R.id.checkTaskLocationLongitudeTextView);
        TextView staff_id = (TextView) view.findViewById(R.id.checkTaskStaffIdTextView);

        location_latitude_text.setText(cursor.getString(cursor.getColumnIndexOrThrow("location_latitude")));
        location_longitude_text.setText(cursor.getString(cursor.getColumnIndexOrThrow("location_longitude")));
        staff_id.setText(cursor.getString(cursor.getColumnIndexOrThrow("staff_id")));
    }
}
