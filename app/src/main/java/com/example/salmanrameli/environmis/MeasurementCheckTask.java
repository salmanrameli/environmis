package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeasurementCheckTask extends AppCompatActivity {
    MeasurementCheckTaskCustomAdapter measurementCheckTaskCursorAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    String name;
    String staff_name;

    ArrayList<TaskToDo> taskToDoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_check_task);

        final ListView checkTaskListView = (ListView) findViewById(R.id.checkTaskListView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Intent intent = getIntent();

        name = intent.getStringExtra(FirebaseStrings.USERNAME);

        databaseReference.child(FirebaseStrings.TODO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                taskToDoArrayList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TaskToDo taskToDo = snapshot.getValue(TaskToDo.class);

                    staff_name = taskToDo.getStaff_name();

                    if(staff_name.equals(name) && taskToDo.getIs_done().equals("false")) {
                        taskToDoArrayList.add(taskToDo);
                    }
                }

                measurementCheckTaskCursorAdapter = new MeasurementCheckTaskCustomAdapter(MeasurementCheckTask.this, taskToDoArrayList);
                checkTaskListView.setAdapter(measurementCheckTaskCursorAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
