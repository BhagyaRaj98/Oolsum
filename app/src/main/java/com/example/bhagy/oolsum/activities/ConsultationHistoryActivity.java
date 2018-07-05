package com.example.bhagy.oolsum.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.ConsultationHistoryAdapter;
import com.example.bhagy.oolsum.objects.Consultation;

import java.util.ArrayList;

public class ConsultationHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        ArrayList<Consultation> consultationItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            consultationItems.add(new Consultation("AlphaClient2","13/01/2018 12:29:34","2500"));
            consultationItems.add(new Consultation("AlphaClient8","13/06/2018 07:34:58","2000"));
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ConsultationHistoryAdapter consultationAdapter = new ConsultationHistoryAdapter(this,consultationItems );

        recyclerView.setAdapter(consultationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
