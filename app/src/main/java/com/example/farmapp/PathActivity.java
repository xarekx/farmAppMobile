package com.example.farmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.farmapp.model.Mob;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

    }

    public void toExpListItem(View view) {

        Intent intent = new Intent(this, MobActivity.class);
        startActivity(intent);
        Toast.makeText(this, "You took exp", Toast.LENGTH_SHORT).show();
    }

    public void toFarmListItem(View view) {

        Intent intent = new Intent(this, MobActivity.class);
        startActivity(intent);
        Toast.makeText(this, "You took farm", Toast.LENGTH_SHORT).show();
    }
}
