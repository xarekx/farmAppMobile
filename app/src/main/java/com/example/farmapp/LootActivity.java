package com.example.farmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.adapters.CustomItemAdapter;
import com.example.farmapp.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class LootActivity extends AppCompatActivity {

    private String mobInfo;
    private static final String TAG = "LootActivity";
    private ArrayList<String> myItems = new ArrayList<>();
    private ArrayList<Integer> myPhotos = new ArrayList<>();
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loot);

        gridView = findViewById(R.id.loot_gv);

        mobInfo = getIntent().getStringExtra("mobPosition");
        String mobPosition = mobInfo.substring(1,2);
        getAllItemsByMobId(mobPosition);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LootActivity.this, ""+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllItemsByMobId(String id) {

        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);

        Call<List<Item>> call = retrofitMobData.getAllItemsByMobId(id);

            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    Log.d(TAG, "onResponse: Success");
                    List<Item> resp = response.body();
                        for (int i = 0; i < resp.size(); i++) {
                            myItems.add(resp.get(i).getItem_name());
                        }
                        for(int i=0;i<myItems.size();i++) {
                            myPhotos.add(R.drawable.booro_cz);
                        }
                        gridView.setAdapter(new CustomItemAdapter(getApplicationContext(),myPhotos,myItems));
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.w(TAG, "onFailure: ", t);
                }
            });



        }

}
