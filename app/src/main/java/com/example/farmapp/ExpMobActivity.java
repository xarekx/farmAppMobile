package com.example.farmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ExpMobActivity extends AppCompatActivity {

    final String TAG = "ExpMobActivity";

    ArrayList<String> expMobList = new ArrayList<>();
    ListView expListView ;
    public static int [] prgmImages = {R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_mob);

        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);
        Call<List<Mob>> call =  retrofitMobData.getAllMobs();

        call.enqueue(new Callback<List<Mob>>() {

            @Override
            public void onResponse(Call<List<Mob>> call, Response<List<Mob>> response) {
                assert response.body()!=null;
                for(int mob=0;mob<response.body().size();mob++) {
                    if(response.body().get(mob).getId_land_mob()==2 ) {
                        expMobList.add(response.body().get(mob).getName_mob());
                    }
                }
                Log.d(TAG,"retrofit success");
                expListView = findViewById(R.id.exp_list_view);
                expListView.setAdapter(new CustomAdapter(expMobList,getApplicationContext(),prgmImages));
            }

            @Override
            public void onFailure(Call<List<Mob>> call, Throwable t) {
                Toast.makeText(ExpMobActivity.this, "we cannot load mobs!", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"retrofit failure",t);

            }
        });


    }
}
