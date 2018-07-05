package com.example.bhagy.oolsum.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.InfoAdapter;
import com.example.bhagy.oolsum.objects.InfoItem;

import java.util.ArrayList;

public class IndependentsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ArrayList<InfoItem> independentInfoList = new ArrayList<>();

        independentInfoList.add(new InfoItem(R.drawable.info, "Name", "Quapt"));
        independentInfoList.add(new InfoItem(R.drawable.info, "Gender", ""));
        independentInfoList.add(new InfoItem(R.drawable.info, "Specialization", "Sports"));
        independentInfoList.add(new InfoItem(R.drawable.info, "Languages Known", ""));
        independentInfoList.add(new InfoItem(R.drawable.info, "Consultation Fee", "$ 10"));
        independentInfoList.add(new InfoItem(R.drawable.info, "Validity Of Consultation", ""));
        independentInfoList.add(new InfoItem(R.drawable.info, "Overall Experience", "6 years"));
        independentInfoList.add(new InfoItem(R.drawable.info, "Qualification", "M.sc Nutrition"));

        RecyclerView recyclerView = findViewById(R.id.independent_info_recycler_list);
        InfoAdapter infoAdapter = new InfoAdapter(this, independentInfoList);

        recyclerView.setAdapter(infoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
