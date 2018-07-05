package com.example.bhagy.oolsum.enums;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bhagy.oolsum.objects.Realm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public enum SecurityRepository {
    INSTANCE;

    private Gson gson = new GsonBuilder().create();

    public void saveRealm(Realm realm, Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("realm", gson.toJson(realm));
        editor.apply();
    }

    public void removeRealm(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("realm");
        editor.apply();
    }

    public Realm getRealm(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        String strUser = preferences.getString("realm", null);

        if (strUser != null)
            return gson.fromJson(strUser, Realm.class);

        return null;
    }

    public void saveToken(String token, Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public void removeToken(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("token");
        editor.apply();
    }

    public String getToken(Context ctx) {
        SharedPreferences preferences = ctx.getSharedPreferences("login_state", Context.MODE_PRIVATE);
        return preferences.getString("token", null);
    }
}
