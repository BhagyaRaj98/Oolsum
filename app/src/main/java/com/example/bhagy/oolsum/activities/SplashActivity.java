package com.example.bhagy.oolsum.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.activities.LoginActivity;
import com.example.bhagy.oolsum.activities.MainScreenActivity;
import com.example.bhagy.oolsum.enums.SecurityRepository;
import com.example.bhagy.oolsum.objects.Realm;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private SecurityRepository securityRepository = SecurityRepository.INSTANCE;
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //purpose delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doStartUp();
            }
        }, SPLASH_TIME_OUT);
    }

    public void doStartUp() {
        Realm realm = securityRepository.getRealm(this);

        Intent landingIntent = getLandingIntent(realm);
        startActivity(landingIntent);
        finish();
    }

    private Intent getLandingIntent(Realm realm) {
        if (realm != null) {
            Log.i(TAG, "Logged in user - " + realm.getEmail());
            // TODO: do following
            // 1. Set user in context. use quapt security context.
           // return new Intent(this, DashboardActivity.class);
            return new Intent(this, MainScreenActivity.class);
        }

        return new Intent(this, LoginActivity.class);
    }
}
