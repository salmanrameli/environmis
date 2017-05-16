package com.example.salmanrameli.environmis;

import android.content.ContentValues;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AssignTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private AssignTaskDbHelper assignTaskDbHelper;
    EditText location_latitude;
    EditText location_longitude;
    Spinner spinner;
    String selected_staff;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        location_latitude = (EditText) findViewById(R.id.assignTaskLocationLatitude);
        location_longitude = (EditText) findViewById(R.id.assignTaskLocationLongitude);

        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> name = new ArrayList<>();

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Staff staff = snapshot.getValue(Staff.class);

                    String role = staff.getRole();

                    if(role.equals("measurement")) {
                        name.add(staff.getUsername());
                    }
                }

                spinner = (Spinner) findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(AssignTask.this);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AssignTask.this, android.R.layout.simple_spinner_dropdown_item, name);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
