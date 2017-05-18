package com.example.salmanrameli.environmis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

        location_latitude.setText("-7.279228");
        location_longitude.setText("112.790819");

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

        Map<String, String> map = new HashMap<>();

        map.put(FirebaseStrings.LOCATION_LATITUDE, location_lat);
        map.put(FirebaseStrings.LOCATION_LONGITUDE, location_long);
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
