package com.example.salmanrameli.environmis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.salmanrameli.db.StaffContract;
import com.example.salmanrameli.db.StaffDbHelper;

import java.util.ArrayList;
import java.util.List;

public class AssignTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private StaffDbHelper staffDbHelper;
    EditText location_latitude;
    EditText location_longitude;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);

        staffDbHelper = new StaffDbHelper(this);

        SQLiteDatabase db = staffDbHelper.getReadableDatabase();

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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
