package com.example.farmapp.Retrofit;

import com.example.farmapp.model.Party;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitPartyData {

    @POST("/party/create")
    Call<Party> createParty(@Body Party party);



}
