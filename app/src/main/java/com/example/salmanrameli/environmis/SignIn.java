package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String user_id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button loginButton = (Button) findViewById(R.id.formSignInButton);
        TextView signUp = (TextView) findViewById(R.id.signUpTextView);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);

                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameInput = (EditText) findViewById(R.id.userNameInputSignIn);
                EditText passwordInput = (EditText) findViewById(R.id.passwordInputSignIn);

                user_id = userNameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(user_id.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignIn.this, "Vacant Field", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth = FirebaseAuth.getInstance();

                    firebaseAuth.signInWithEmailAndPassword(user_id, password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                firebaseAuth = FirebaseAuth.getInstance();
                                firebaseUser = firebaseAuth.getCurrentUser();
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = firebaseDatabase.getReference();

                                String _id = firebaseUser.getUid();

                                databaseReference.child(FirebaseStrings.USERS).child(_id).child(FirebaseStrings.ROLE).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String role = dataSnapshot.getValue(String.class);

                                        if(role.equals(FirebaseStrings.VALIDATOR_ROLE)) {
                                            Intent intent = new Intent(SignIn.this, HomeValidatorStaff.class);

                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                            startActivity(intent);
                                        }
                                        if(role.equals(FirebaseStrings.MEASUREMENT_ROLE)) {
                                            Intent intent = new Intent(SignIn.this, HomeMeasurementStaff.class);

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
                        }
                    });
                }
            }
        });

    }
}