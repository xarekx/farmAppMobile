package com.example.farmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitMobData;
import com.example.farmapp.adapters.CustomAdapter;
import com.example.farmapp.itemActivity.LootActivity;
import com.example.farmapp.model.Mob;

import java.util.ArrayList;
import java.util.HashMap;
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
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

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
                String myMob="";
                for(int i=0;i<arrayList.size();i++) {
                    if(arrayList.get(i).values().contains(parent.getItemAtPosition(position))) {
                        myMob = arrayList.get(i).values().toString();
                    }
                }
                Intent intent = new Intent(getApplicationContext(), LootActivity.class);
                intent.putExtra("mobPosition",myMob); // sending position not mob_id
                startActivity(intent);
            }
        });

    }

    public void getAllExpMobs() {
        final ArrayList<String> value = new ArrayList<>();
        RetrofitMobData retrofitMobData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitMobData.class);
        Call<List<Mob>> call =  retrofitMobData.getAllMobs();

        call.enqueue(new Callback<List<Mob>>() {

            @Override
            public void onResponse(Call<List<Mob>> call, Response<List<Mob>> response) {
                List<Mob> resp = response.body();

                for(int mob=0;mob<resp.size();mob++) {
                    HashMap<String,String> hashMap = new HashMap<>();
                    if(resp.get(mob).getId_land_mob()==2 ) {
                        if(resp.get(mob).getName_mob().contains("_")) {
                            String myMob = resp.get(mob).getName_mob().replace("_"," ");
                            hashMap.put("mob_id",""+resp.get(mob).getId_mob());
                            hashMap.put("mob_name",myMob);
                            expMobList.add(myMob);
                        } else {
                            expMobList.add(resp.get(mob).getName_mob());
                            hashMap.put("mob_id",""+resp.get(mob).getId_mob());
                            hashMap.put("mob_name",resp.get(mob).getName_mob());
                        }
                    }
                    arrayList.add(hashMap);

                }
                assert resp!=null;
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
