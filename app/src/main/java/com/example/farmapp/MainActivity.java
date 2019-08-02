package com.example.farmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText mLogin = findViewById(R.id.Et_login);
        final EditText mPassword = findViewById(R.id.Et_password);
        Button mSignInButton = findViewById(R.id.sign_button);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mLogin.getText().toString();
                String password = mPassword.getText().toString();
                if(login.equals("arek") && password.equals("123")) {
                    Intent intent = new Intent(getApplicationContext(), PathActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome in FarmApp!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: true");
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Login or Password!", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "onClick: failure");
                }
            }
        });
    }
}
