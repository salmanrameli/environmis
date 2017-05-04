package com.example.salmanrameli.environmis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUpButtonOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);

        startActivity(intent);
    }

    public void signInButtonOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SignIn.class);

        startActivity(intent);
    }

    protected void OnDestroy() {
        super.onDestroy();
    }
}
