package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    String id, password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        final EditText user_id = (EditText) findViewById(R.id.editTextEmail);
        final EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        Button signUpButton = (Button) findViewById(R.id.buttonSIgnUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = user_id.getText().toString();
                password = passwordEditText.getText().toString();
                confirm_password = confirmPassword.getText().toString();

                if(id.equals("") || password.equals("") || confirm_password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field vacant", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirm_password)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Sign up success!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    firebaseAuth.signInWithEmailAndPassword(id, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(SignUp.this, FillAccountDetails.class);

                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                            }
                        }
                    });

                }
            }
        });
    }

    protected void OnDestroy() {
        super.onDestroy();
    }
}