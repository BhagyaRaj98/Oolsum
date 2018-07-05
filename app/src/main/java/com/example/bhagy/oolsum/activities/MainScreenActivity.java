package com.example.bhagy.oolsum.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.fragments.HomeFragment;
import com.example.bhagy.oolsum.fragments.MenuFragment;
import com.example.bhagy.oolsum.fragments.ProfileFragment;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class MainScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        loadFragment(new HomeFragment());
        resetActionBar("Home");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.navigation_menu:
                    fragment = new MenuFragment();
                    resetActionBar((String) item.getTitle());
                break;

            case R.id.navigation_home:
                    fragment = new HomeFragment();
                    resetActionBar((String) item.getTitle());
                break;

            case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    resetActionBar((String) item.getTitle());
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {

        if (bottomNavigationView.getSelectedItemId() != R.id.navigation_home) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            loadFragment(new HomeFragment());
        } else
            super.onBackPressed();
    }

    private boolean loadFragment(android.support.v4.app.Fragment currentFragment) {

        if (currentFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, currentFragment)
                    .commit();
            return true;
        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void resetActionBar(String title) {

        requireNonNull(getSupportActionBar()).setShowHideAnimationEnabled(false);
        if (title.equals("Profile")) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            requireNonNull(getSupportActionBar()).hide();
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(title);
            requireNonNull(getSupportActionBar()).show();
        }

    }
}
