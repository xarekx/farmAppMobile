package com.example.farmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PartyNameActivity extends AppCompatActivity {

    final String TAG = "PartyNameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_name);

        final EditText mPartyName_Et = findViewById(R.id.type_party_name_et);
        final Button mPartyName_Btn = findViewById(R.id.save_name_btn);

        mPartyName_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PartyActivity.class);
                intent.putExtra("party_name",mPartyName_Et.getText().toString());
                startActivity(intent);
            }
        });
    }


}
