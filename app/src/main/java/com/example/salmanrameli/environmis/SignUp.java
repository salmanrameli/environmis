package com.example.salmanrameli.environmis;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.salmanrameli.db.StaffContract;
import com.example.salmanrameli.db.StaffDbHelper;

public class SignUp extends AppCompatActivity {
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        StaffDbHelper staffDbHelper = new StaffDbHelper(this);

        final SQLiteDatabase db = staffDbHelper.getWritableDatabase();

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText user_id = (EditText) findViewById(R.id.editTextId);
        final EditText usernameEditText = (EditText) findViewById(R.id.editTextUserName);
        final EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        Button signUpButton = (Button) findViewById(R.id.buttonCreateAccount);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user_id.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirm_password = confirmPassword.getText().toString();
                int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedRadioButton);

                if(id.equals("") || username.equals("") || password.equals("") || confirm_password.equals("") || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Field vacant", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirm_password)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues values;

                    switch (selectedRadioButton) {
                        case R.id.measurementStaffRadioButton:
                            values = new ContentValues();

                            values.put(StaffContract.StaffEntry._ID, id);
                            values.put(StaffContract.StaffEntry.COL_STAFF_NAME, username);
                            values.put(StaffContract.StaffEntry.COL_STAFF_PASS, password);
                            values.put(StaffContract.StaffEntry.COL_STAFF_ROLE, "measurement");

                            db.insertWithOnConflict(StaffContract.StaffEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                            Toast.makeText(SignUp.this, "Sign up success!", Toast.LENGTH_LONG).show();

                            break;

                        case R.id.reportValidatorRadioButton: {
                            values = new ContentValues();

                            values.put(StaffContract.StaffEntry._ID, id);
                            values.put(StaffContract.StaffEntry.COL_STAFF_NAME, username);
                            values.put(StaffContract.StaffEntry.COL_STAFF_PASS, password);
                            values.put(StaffContract.StaffEntry.COL_STAFF_ROLE, "validator");

                            db.insertWithOnConflict(StaffContract.StaffEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                            Toast.makeText(SignUp.this, "Sign up success!", Toast.LENGTH_LONG).show();

                            break;
                        }
                    }

                    finish();
                }
            }
        });
    }

    protected void OnDestroy() {
        super.onDestroy();
    }
}