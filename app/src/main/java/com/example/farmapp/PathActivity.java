package com.example.farmapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class PathActivity extends AppCompatActivity {

    final String TAG = "PathActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

    }

    public void toExpListItem(View view) {

        Intent intent = new Intent(this, ExpMobActivity.class);
        startActivity(intent);
        Toast.makeText(this, "You took exp", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"you choose exp List");
    }

    public void toFarmListItem(View view) {

        Intent intent = new Intent(this, FarmMobActivity.class);
        startActivity(intent);
        Toast.makeText(this, "You took farm", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"you choose farm list");
    }
}
