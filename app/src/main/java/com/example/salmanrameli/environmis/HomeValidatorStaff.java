package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HomeValidatorStaff extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_validator_staff);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

//        Map<String, String> map = new HashMap<>();
//
//        map.put(FirebaseStrings.LOCATION_NAME, "Teknik Informatika ITS");
//        map.put(FirebaseStrings.LOCATION_LATITUDE, "-7.279581");
//        map.put(FirebaseStrings.LOCATION_LONGITUDE, "112.797595");
//
//        DatabaseReference _id = databaseReference.child(FirebaseStrings.LOCATION).push();
//        String key_id = _id.getKey();
//
//        map.put("location_key", key_id);
//
//        _id.setValue(map);
//
//        map = new HashMap<>();
//
//        map.put(FirebaseStrings.LOCATION_NAME, "Kantin Pusat ITS");
//        map.put(FirebaseStrings.LOCATION_LATITUDE, "-7.284059");
//        map.put(FirebaseStrings.LOCATION_LONGITUDE, "112.793980");
//
//        _id = databaseReference.child(FirebaseStrings.LOCATION).push();
//        key_id = _id.getKey();
//
//        map.put("location_key", key_id);
//
//        _id.setValue(map);
//
//        map = new HashMap<>();
//
//        map.put(FirebaseStrings.LOCATION_NAME, "SMP Negeri 6 Surabaya");
//        map.put(FirebaseStrings.LOCATION_LATITUDE, "-7.273889");
//        map.put(FirebaseStrings.LOCATION_LONGITUDE, "112.748548");
//
//        _id = databaseReference.child(FirebaseStrings.LOCATION).push();
//        key_id = _id.getKey();
//
//        map.put("location_key", key_id);
//
//        _id.setValue(map);
//
//        map = new HashMap<>();
//
//        map.put(FirebaseStrings.LOCATION_NAME, "SMA Negeri 2 Surabaya");
//        map.put(FirebaseStrings.LOCATION_LATITUDE, "-7.256216");
//        map.put(FirebaseStrings.LOCATION_LONGITUDE, "112.749536");
//
//        _id = databaseReference.child(FirebaseStrings.LOCATION).push();
//        key_id = _id.getKey();
//
//        map.put("location_key", key_id);
//
//        _id.setValue(map);
    }

    public void viewReportButtonOnClick(View view)
    {
        Intent intent = new Intent(this, IndexReport.class);

        startActivity(intent);
    }

    public void assignTaskButtonOnClick(View view)
    {
        Intent intent = new Intent(this, AssignTask.class);

        startActivity(intent);
    }

    public void validatorSignOutButtonOnClick(View view)
    {
        firebaseAuth.signOut();

        Intent intent = new Intent(HomeValidatorStaff.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
