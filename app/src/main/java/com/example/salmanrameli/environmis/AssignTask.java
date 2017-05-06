package com.example.salmanrameli.environmis;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salmanrameli.db.AssignTaskContract;
import com.example.salmanrameli.db.AssignTaskDbHelper;
import com.example.salmanrameli.db.StaffContract;
import com.example.salmanrameli.db.StaffDbHelper;

import java.util.ArrayList;
import java.util.List;

public class AssignTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private StaffDbHelper staffDbHelper;
    private AssignTaskDbHelper assignTaskDbHelper;
    EditText location_latitude;
    EditText location_longitude;
    Spinner spinner;
    String selected_staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);

        staffDbHelper = new StaffDbHelper(this);

        SQLiteDatabase db = staffDbHelper.getReadableDatabase();

        location_latitude = (EditText) findViewById(R.id.assignTaskLocationLatitude);
        location_longitude = (EditText) findViewById(R.id.assignTaskLocationLongitude);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        Cursor cursor = db.query(StaffContract.StaffEntry.TABLE, new String[]{StaffContract.StaffEntry._ID}, StaffContract.StaffEntry.COL_STAFF_ROLE + " = ?", new String[]{"measurement"}, null, null, null);

        List<String> name = new ArrayList<>();

        while(cursor.moveToNext())
        {
            name.add(cursor.getString(cursor.getColumnIndex("_id")));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, name);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        cursor.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_staff = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void assignTaskButtonOnClick(View view) {
        String location_lat = String.valueOf(location_latitude.getText().toString());
        String location_long = String.valueOf(location_longitude.getText().toString());

        assignTaskDbHelper = new AssignTaskDbHelper(this);

        SQLiteDatabase db = assignTaskDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(AssignTaskContract.AssignTaskEntry.COL_LOCATION_LATITUDE, location_lat);
        values.put(AssignTaskContract.AssignTaskEntry.COL_LOCATION_LONGITUDE, location_long);
        values.put(AssignTaskContract.AssignTaskEntry.COL_STAFF_ID, selected_staff);
        values.put(AssignTaskContract.AssignTaskEntry.COL_IS_DONE, "false");

        db.insertWithOnConflict(AssignTaskContract.AssignTaskEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        finish();
    }
}
