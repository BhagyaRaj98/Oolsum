package com.example.bhagy.oolsum.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.TabPagerAdapter;
import com.example.bhagy.oolsum.fragments.HospitalsFragment;
import com.example.bhagy.oolsum.fragments.IndependentsFragment;
import com.example.bhagy.oolsum.fragments.OthersFragment;

import java.util.Objects;


public class ConsultDietitianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_with_tabs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        }

        ViewPager consultDietitianViewPager = findViewById(R.id.tabs_viewpager);

        TabPagerAdapter tabSliderAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabSliderAdapter.addFragment(new HospitalsFragment(), "Hospitals");
        tabSliderAdapter.addFragment(new IndependentsFragment(), "Independents");
        tabSliderAdapter.addFragment(new OthersFragment(), "Others");
        consultDietitianViewPager.setAdapter(tabSliderAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(consultDietitianViewPager);

    }
}
