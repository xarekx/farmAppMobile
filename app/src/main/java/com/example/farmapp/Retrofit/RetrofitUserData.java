package com.example.farmapp.Retrofit;

import com.example.farmapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitUserData {


    @GET("/user/users")
    Call<List<User>> getAllUsers();
}
