package com.example.farmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class LootActivity extends AppCompatActivity {

    private String mobInfo;
    private static final String TAG = "LootActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loot);

         mobInfo = getIntent().getStringExtra("mobPosition");
         String mobPosition = mobInfo.substring(1,2);

        System.out.println(mobPosition);

        getAllItemsByMobId(mobPosition);
    }


    public void getAllItemsByMobId(String id) {

        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);

        Call<List<Item>> call = retrofitMobData.getAllItemsByMobId(id);

            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                    List<Item> resp = response.body();
                    if(resp != null ) {
                        for (int i = 0; i < resp.size(); i++) {
                            System.out.println(resp.get(i).getItem_name());
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {

                    Log.w(TAG, "onFailure: ", t);

                }
            });
        }

}
