package com.example.bhagy.oolsum.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bhagy.oolsum.activities.ConsultDietitianActivity;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.activities.ConsultationHistoryActivity;
import com.example.bhagy.oolsum.adapters.AutomaticScrollAdapter;
import com.example.bhagy.oolsum.adapters.TabPagerAdapter;
import com.example.bhagy.oolsum.enums.SecurityRepository;
import com.example.bhagy.oolsum.objects.Realm;

import java.util.Objects;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

import static java.util.Objects.requireNonNull;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private SecurityRepository securityRepository = SecurityRepository.INSTANCE;
//    private static final int USER_WITHOUT_DIET_CHART = 0;
//    private static final int USER_WITH_DIET_CHART = 1;
//    private static final int DIETITIAN_WITHOUT_VERIFICATION = 2;
//    private static final int DIETITIAN_WITH_VERIFICATION = 3;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;

        Realm realm = securityRepository.getRealm(Objects.requireNonNull(getContext()));
        assert realm != null;

        String userRole = realm.getRoles().toString();
        userRole = userRole.substring(1,userRole.length()-1);

//        int homeType=0;
//        if(userRole.equals("General"))
//            homeType = 0;
//        else if(userRole.equals("Dietician"))
//            homeType=3;

        switch(userRole) {

            case "General1":
                rootView = inflater.inflate(R.layout.user_home_without_dietchart_fragment, container, false);
                setUserWithoutDietChart(rootView);
                break;

            case "General":
                rootView = inflater.inflate(R.layout.viewpager_with_tabs, container, false);
                setUserWithDietChart(rootView);
                break;

            case "Dietitian_Not_Verified":
                rootView = inflater.inflate(R.layout.dietitian_home_without_verification_fragment, container, false);
                setDietitianWithoutVerification(rootView);
                break;

            case "Dietician":
                rootView = inflater.inflate(R.layout.dietitian_home_with_verification_fragment, container, false);
                setDietitianWithVerification(rootView);
                break;
        }

        return rootView;
    }

    private void setUserWithoutDietChart(View rootView) {
        Button btn_consultNow = rootView.findViewById(R.id.btn_consult_now);
        btn_consultNow.setOnClickListener(this);

        AutoScrollViewPager autoInstScrollViewPager = rootView.findViewById(R.id.slider_view_pager);
        int[] images = new int[]{R.drawable.hospital, R.drawable.hospital, R.drawable.hospital, R.drawable.hospital};
        String[] steps = new String[]{"Step 1", "Step 2", "Step 3", "Step 4"};
        String[] instructions = new String[]{" Select Dietitian", "Make Payment", "Consult Dietitian", "Get Diet Chart"};

        AutomaticScrollAdapter sliderAdapter = new AutomaticScrollAdapter(getContext(), images, steps, instructions);
        autoInstScrollViewPager.setAdapter(sliderAdapter);

        autoInstScrollViewPager.setScrollFactor(8);
        autoInstScrollViewPager.startAutoScroll(3000);

        //Setting the Circle Indicator for AutoScroll Viewpager
        CircleIndicator indicator = rootView.findViewById(R.id.circle_indicator);
        indicator.setViewPager(autoInstScrollViewPager);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setUserWithDietChart(View rootView) {
        requireNonNull(((AppCompatActivity) requireNonNull(getActivity())).getSupportActionBar()).setElevation(0);

        ViewPager dietChartAssignedViewPager = rootView.findViewById(R.id.tabs_viewpager);

        TabPagerAdapter tabSliderAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabSliderAdapter.addFragment(new DietChartFragment(), "Diet Chart");
        tabSliderAdapter.addFragment(new InstructionsFragment(), "Instructions");
        dietChartAssignedViewPager.setAdapter(tabSliderAdapter);

        TabLayout tabLayout = rootView.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(dietChartAssignedViewPager);

    }

    private void setDietitianWithoutVerification(View rootView) {
    }

    private void setDietitianWithVerification(View rootView) {
        LinearLayout ll_consultation_window, ll_pending_diet_charts, ll_manage_templates, ll_consultation_history;

        ll_consultation_window = rootView.findViewById(R.id.ll_consultation_window);
        ll_consultation_window.setOnClickListener(this);
        ll_pending_diet_charts = rootView.findViewById(R.id.ll_pending_diet_charts);
        ll_pending_diet_charts.setOnClickListener(this);
        ll_manage_templates = rootView.findViewById(R.id.ll_manage_templates);
        ll_manage_templates.setOnClickListener(this);
        ll_consultation_history = rootView.findViewById(R.id.ll_consultation_history);
        ll_consultation_history.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_consult_now:
                Intent consultNowIntent = new Intent(getContext(), ConsultDietitianActivity.class);
                startActivity(consultNowIntent);
                break;

            case R.id.ll_consultation_window:

                break;

            case R.id.ll_pending_diet_charts:
                break;

            case R.id.ll_manage_templates:
                break;

            case R.id.ll_consultation_history:
                Intent consultationsIntent = new Intent(getContext(), ConsultationHistoryActivity.class);
                startActivity(consultationsIntent);
                break;

        }
    }

}
