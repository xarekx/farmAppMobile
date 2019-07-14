package com.example.farmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.adapters.CustomAdapter;
import com.example.farmapp.model.Mob;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobActivity extends AppCompatActivity {

    private ArrayList<String> listItem = new ArrayList<>();
    ListView mListView;
    public static int [] prgmImages = {R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob);

        final RetrofitMobData getDataService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);
        Call<List<Mob>> call = getDataService.getAllMobs();
        call.enqueue(new Callback<List<Mob>>() {
            @Override
            public void onResponse(Call<List<Mob>> call, Response<List<Mob>> response) {
                for (int i=0; i<response.body().size(); i++) {
                    listItem.add(response.body().get(i).getName_mob());
                }
                System.out.println(listItem);
                mListView = findViewById(R.id.list_view);
                mListView.setAdapter(new CustomAdapter(listItem,getApplicationContext(),prgmImages));
            }


            @Override
            public void onFailure(Call<List<Mob>> call, Throwable t) {

            }
        });
        }
    }
