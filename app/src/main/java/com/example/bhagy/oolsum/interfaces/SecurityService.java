package com.example.bhagy.oolsum.interfaces;

import com.example.bhagy.oolsum.objects.Realm;
import com.example.bhagy.oolsum.objects.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface SecurityService {

    @POST("login/users")
    Call<Realm> login(@Body Token token);

    @GET("logout/users")
    Call<Void> logout(@Header("x-token") String token);
}
