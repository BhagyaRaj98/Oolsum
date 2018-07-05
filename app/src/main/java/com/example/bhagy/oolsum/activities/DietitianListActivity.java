package com.example.bhagy.oolsum.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.IndependentAdapter;
import com.example.bhagy.oolsum.objects.Independent;

import java.util.ArrayList;
import java.util.List;

public class DietitianListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);


        List<Independent> dietitians = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dietitians.add(new Independent("offline", "4.0", R.drawable.ic_user, "ASDFG", "M.sc Nutrition",
                    "7 years Experience", "Diabetic", "300"));
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        IndependentAdapter dietitianAdapter = new IndependentAdapter(this, dietitians);

        recyclerView.setAdapter(dietitianAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
