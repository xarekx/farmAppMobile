package com.example.farmapp.Retrofit;

import com.example.farmapp.model.Mob;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitMobData {

    @GET("/mobs/all")
    Call<List<Mob>> getAllMobs();

    @GET("mobs/{id}")
    Call<Mob> getMob(@Path("id") int id);

}
