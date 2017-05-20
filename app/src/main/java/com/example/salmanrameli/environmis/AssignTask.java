package com.example.salmanrameli.environmis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner spinnerLocation;

    String selected_staff;
    String selected_location;
    String location_latitude;
    String location_longitude;

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

        databaseReference.child(FirebaseStrings.USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> name = new ArrayList<>();

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Staff staff = snapshot.getValue(Staff.class);

                    String role = staff.getRole();

                    if(role.equals(FirebaseStrings.MEASUREMENT_ROLE)) {
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

        databaseReference.child(FirebaseStrings.LOCATION).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> location_names = new ArrayList<String>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Location location = snapshot.getValue(Location.class);

                    String location_name = location.getLocation_name();

                    location_names.add(location_name);
                }

                spinnerLocation = (Spinner) findViewById(R.id.spinnerLocationName);
                spinnerLocation.setOnItemSelectedListener(AssignTask.this);

                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(AssignTask.this, android.R.layout.simple_spinner_dropdown_item, location_names);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerLocation.setAdapter(arrayAdapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner parentSpinner = (Spinner) parent;

        switch(parentSpinner.getId()) {
            case R.id.spinner:
                selected_staff = parent.getItemAtPosition(position).toString();

                break;

            case R.id.spinnerLocationName:
                selected_location = parent.getItemAtPosition(position).toString();

                databaseReference.child(FirebaseStrings.LOCATION).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Location location = snapshot.getValue(Location.class);

                            String _location_name = location.getLocation_name();

                            if(_location_name.equals(selected_location)) {
                                location_latitude = location.getLocation_latitude();
                                location_longitude = location.getLocation_longitude();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void assignTaskButtonOnClick(View view) {
        Map<String, String> map = new HashMap<>();

        map.put(FirebaseStrings.LOCATION_LATITUDE, location_latitude);
        map.put(FirebaseStrings.LOCATION_LONGITUDE, location_longitude);
        map.put(FirebaseStrings.STAFF_ID, selected_staff);
        map.put(FirebaseStrings.IS_DONE, "false");

        DatabaseReference _id = databaseReference.child(FirebaseStrings.TODO).push();
        String key_id = _id.getKey();

        map.put("todo_key", key_id);

        _id.setValue(map);

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        finish();
    }
}
