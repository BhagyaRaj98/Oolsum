package com.example.bhagy.oolsum.enums;

import com.example.bhagy.oolsum.BuildConfig;
import com.example.bhagy.oolsum.interfaces.SecurityService;
import com.example.bhagy.oolsum.interfaces.UserPublicService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public enum RestServiceHelper {
    INSTANCE;

    private UserPublicService userPublicService;
    private SecurityService securityService;

    RestServiceHelper() {
        Retrofit publicDomain = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + "/services/registration/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        userPublicService = publicDomain.create(UserPublicService.class);

        Retrofit securityDomain = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + "/services/security/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        securityService = securityDomain.create(SecurityService.class);
    }

    public UserPublicService getUserPublicService() {
        return userPublicService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }
}
