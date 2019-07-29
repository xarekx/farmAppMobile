package com.example.farmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

@SuppressWarnings("ALL")
public class ExpMobActivity extends AppCompatActivity {

    final String TAG = "ExpMobActivity";

    ArrayList<String> expMobList = new ArrayList<>();
    ListView expListView ;
    CustomAdapter customAdapter;

    public static int [] prgmImages = {R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz,R.drawable.booro_cz};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_mob);
        expListView = findViewById(R.id.exp_list_view);

        getAllExpMobs();

        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),LootActivity.class);
                intent.putExtra("mobPosition",position+1); // sending position not mob_id
                startActivity(intent);
            }
        });

    }
        // TO_
    public void getAllExpMobs() {

        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);
        Call<List<Mob>> call =  retrofitMobData.getAllMobs();

        call.enqueue(new Callback<List<Mob>>() {

            @Override
            public void onResponse(Call<List<Mob>> call, Response<List<Mob>> response) {
                List<Mob> resp = response.body();
                assert resp!=null;
                for(int mob=0;mob<resp.size();mob++) {
                    if(resp.get(mob).getId_land_mob()==2 ) {
                        System.out.println(resp.get(mob).getId_mob());
                        if(resp.get(mob).getName_mob().contains("_")) {
                            String myMob = resp.get(mob).getName_mob().replace("_"," ");
                            expMobList.add(myMob);

                        } else {
                            expMobList.add(resp.get(mob).getName_mob());
                        }
                    }
                }
                customAdapter = new CustomAdapter(expMobList,getApplicationContext(),prgmImages);
                expListView.setAdapter(customAdapter);
                Log.d(TAG,"retrofit success");
            }

            @Override
            public void onFailure(Call<List<Mob>> call, Throwable t) {
                Toast.makeText(ExpMobActivity.this, "we cannot load mobs!", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"retrofit failure",t);

            }
        });


    }
}
