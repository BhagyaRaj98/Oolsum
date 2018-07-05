package com.example.bhagy.oolsum.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.InfoAdapter;
import com.example.bhagy.oolsum.objects.InfoItem;

import java.util.ArrayList;

public class HospitalsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        ArrayList<InfoItem> hospitalInfoList = new ArrayList<>();


        hospitalInfoList.add(new InfoItem(R.drawable.info, "Institution Name", "Hospital 1"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Diet Specializations", "Kids"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Consultation Validity", "2 days"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Average Consultation Cost", "$ 10"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Average Rating", "1.0"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Contact Number", "9898989898"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Address", "Chennai"));
        hospitalInfoList.add(new InfoItem(R.drawable.info, "Website", "www.Hospital.com"));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        InfoAdapter infoAdapter = new InfoAdapter(this, hospitalInfoList);

        recyclerView.setAdapter(infoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
