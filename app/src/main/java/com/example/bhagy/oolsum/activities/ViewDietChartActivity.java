package com.example.bhagy.oolsum.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.TabPagerAdapter;
import com.example.bhagy.oolsum.fragments.DietChartFragment;
import com.example.bhagy.oolsum.fragments.InstructionsFragment;

public class ViewDietChartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diet_chart);

        ViewPager dietChartAssignedViewPager = findViewById(R.id.tabs_viewpager);

        TabPagerAdapter tabSliderAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabSliderAdapter.addFragment(new DietChartFragment(), "Diet Chart");
        tabSliderAdapter.addFragment(new InstructionsFragment(), "Instructions");
        dietChartAssignedViewPager.setAdapter(tabSliderAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(dietChartAssignedViewPager);

        RelativeLayout consultationDetails = findViewById(R.id.consultation_details);
        consultationDetails.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.consultation_details:
                Intent viewDietChartIntent = new Intent(this, IndependentsInfoActivity.class);
                startActivity(viewDietChartIntent);
                break;

        }

    }
}
