package com.example.salmanrameli.environmis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salmanrameli.db.StaffContract;
import com.example.salmanrameli.db.StaffDbHelper;

public class SignIn extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String id_key = "id_key";
    public static final String password_key = "password_key";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void loginButtonOnClick(View view) {
        EditText userNameInput = (EditText) findViewById(R.id.userNameInputSignIn);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInputSignIn);

        String user_id = userNameInput.getText().toString();
        String password = passwordInput.getText().toString();

        StaffDbHelper staffDbHelper = new StaffDbHelper(this);

        SQLiteDatabase db = staffDbHelper.getReadableDatabase();

        Cursor cursor = db.query(StaffContract.StaffEntry.TABLE, null, StaffContract.StaffEntry._ID + " = ?", new String[]{user_id}, null, null, null, null);

        if(cursor.getCount() < 1) {
            cursor.close();

            Toast.makeText(SignIn.this, "User not found", Toast.LENGTH_LONG).show();
        }

        cursor.moveToFirst();

        String db_password = cursor.getString(cursor.getColumnIndex("staff_password"));

        if(password.equals(db_password)) {
            String username_session = cursor.getString(cursor.getColumnIndex("staff_name"));
            String role_session = cursor.getString(cursor.getColumnIndex("staff_role"));

            Toast.makeText(SignIn.this, "Login success for\nuser: " + username_session + "\nrole: " + role_session, Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(id_key, user_id);
            editor.putString(password_key, password);
            editor.apply();

            if(role_session.equals("measurement")) {
                Intent intent = new Intent(SignIn.this, HomeMeasurementStaff.class);

                startActivity(intent);
            } else if(role_session.equals("validator")) {
                Intent intent = new Intent(SignIn.this, HomeValidatorStaff.class);

                startActivity(intent);
            }
        }
        else {
            Toast.makeText(SignIn.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
        }
    }
}