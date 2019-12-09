package com.example.farmapp.Retrofit;

import com.example.farmapp.model.Party;
import com.example.farmapp.model.PartyMember;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitPartyData {

    @POST("/party/create")
    Call<Party> createParty(@Body Party party);


    @POST("/members/add")
    Call<PartyMember> saveMembers(@Body PartyMember partyMember);

    @GET("party/maxindex")
    Call<Integer> getMaxIndex();

}
