package com.example.farmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.adapters.CustomAdapter;
import com.example.farmapp.model.Mob;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmMobActivity extends AppCompatActivity {

    final String TAG = "FarmMobActivity";

    private ArrayList<String> farmMobList = new ArrayList<>();
    ListView farmListView;
    public static int [] prgmImages = {R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob);


        final RetrofitMobData getMobService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);
        Call<List<Mob>> call = getMobService.getAllMobs();
        call.enqueue(new Callback<List<Mob>>() {
            @Override
            public void onResponse(Call<List<Mob>> call, Response<List<Mob>> response) {

                assert response.body()!=null;
                for (int mob=0; mob<response.body().size(); mob++) {
                    if (response.body().get(mob).getId_land_mob() == 1) {
                        farmMobList.add(response.body().get(mob).getName_mob());
                    }
                }
                farmListView = findViewById(R.id.farm_list_view);
                farmListView.setAdapter(new CustomAdapter(farmMobList,getApplicationContext(),prgmImages));
                Log.d(TAG,"retrofit success");
            }

            @Override
            public void onFailure(Call<List<Mob>> call, Throwable t) {
                Toast.makeText(FarmMobActivity.this, "we cannot load mobs!", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"retrofit failure", t);

            }
        });
        }



    }
