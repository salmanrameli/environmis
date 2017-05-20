package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private String _id;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null) {
            _id = firebaseUser.getUid();

            databaseReference.child(FirebaseStrings.USERS).child(_id).child(FirebaseStrings.ROLE).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    role = dataSnapshot.getValue().toString();

                    if(role.equals(FirebaseStrings.VALIDATOR_ROLE)) {
                        Intent intent = new Intent(MainActivity.this, HomeValidatorStaff.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }
                    if(role.equals(FirebaseStrings.MEASUREMENT_ROLE)) {
                        Intent intent = new Intent(MainActivity.this, HomeMeasurementStaff.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(firebaseUser == null) {
            Intent intent = new Intent(MainActivity.this, SignIn.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    public void signUpButtonOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);

        startActivity(intent);
    }

    protected void OnDestroy() {
        super.onDestroy();
    }
}
