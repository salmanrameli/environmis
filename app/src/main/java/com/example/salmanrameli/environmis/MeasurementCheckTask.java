package com.example.salmanrameli.environmis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salmanrameli.db.AssignTaskContract;
import com.example.salmanrameli.db.AssignTaskDbHelper;

public class MeasurementCheckTask extends AppCompatActivity {
    MeasurementCheckTaskCursorAdapter measurementCheckTaskCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_check_task);

        AssignTaskDbHelper assignTaskDbHelper = new AssignTaskDbHelper(this);
        ListView checkTaskListView = (ListView) findViewById(R.id.checkTaskListView);

        String user_id = getIntent().getStringExtra("user_id_session");

        Toast.makeText(this, "user id: " + user_id, Toast.LENGTH_SHORT).show();

        SQLiteDatabase db = assignTaskDbHelper.getReadableDatabase();

        Cursor cursor = db.query(AssignTaskContract.AssignTaskEntry.TABLE,
                null,
                AssignTaskContract.AssignTaskEntry.COL_STAFF_ID + " = ? and " + AssignTaskContract.AssignTaskEntry.COL_IS_DONE + " = ?", new String[]{user_id, "false"}, null, null, null);

        measurementCheckTaskCursorAdapter = new MeasurementCheckTaskCursorAdapter(this, cursor);
        checkTaskListView.setAdapter(measurementCheckTaskCursorAdapter);

    }
}
