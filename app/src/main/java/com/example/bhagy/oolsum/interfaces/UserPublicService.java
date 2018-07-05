package com.example.bhagy.oolsum.interfaces;

import com.example.bhagy.oolsum.objects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface UserPublicService {

    @GET("users")
    Call<String> getProfileMatch(@Query("uid") String uid, @Query("email") String email);

    @POST("users")
    Call<String> register(@Body User user);
}
