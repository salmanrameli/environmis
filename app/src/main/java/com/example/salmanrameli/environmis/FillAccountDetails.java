package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.salmanrameli.db.FirebaseStrings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillAccountDetails extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private EditText editTextName;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonCreateAccount;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_account_details);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        final String id = firebaseUser.getUid();

        editTextName = (EditText) findViewById(R.id.editTextName);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedRadioButton);

                if(name.equals("") || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(FillAccountDetails.this, "Vacant Field", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child(FirebaseStrings.USERS).child(id).child(FirebaseStrings.USERNAME).setValue(name);
                    databaseReference.child(FirebaseStrings.USERS).child(id).child(FirebaseStrings.ROLE).setValue(FirebaseStrings.MEASUREMENT_ROLE);

                    Toast.makeText(FillAccountDetails.this, "Sign up success!", Toast.LENGTH_LONG).show();

                    intent = new Intent(FillAccountDetails.this, HomeValidatorStaff.class);

                    startActivity(intent);
                }
            }
        });
    }
}
