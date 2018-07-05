package com.example.bhagy.oolsum.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.util.*;

public class PrefManager {

    public static void putSharedPreferencesString(Context context, String key, String val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putString(key, val);
        edit.apply();

    }

    public static String getSharedPreferencesString(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static boolean checkIsKeyExist(Context c, String key) {

        return getSharedPreferencesString(c, key) != null && !getSharedPreferencesString(c, key).equals("");
    }

    public static void clearSharedPreferences(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
    }

    public static void printData(AppCompatActivity a) {

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(a);
        HashMap hashMap = (HashMap) sharedPreference.getAll();
        Set s = hashMap.keySet();

        for (Object value : s) {
            String key = (String) value;
            System.out.println("Key: " + key + " -- Value: " + sharedPreference.getString(key, " (EMPTY) "));
        }

    }

}