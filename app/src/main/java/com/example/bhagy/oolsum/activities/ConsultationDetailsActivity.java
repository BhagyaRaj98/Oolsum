package com.example.bhagy.oolsum.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.HorizontalRowAdapter;

import java.util.ArrayList;

public class ConsultationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        ArrayList<String> detailNames = new ArrayList<>();
        detailNames.add("text1");
        detailNames.add("text2");
        detailNames.add("text3");
        detailNames.add("text4");

        ArrayList<String> detailValues = new ArrayList<>();
        detailValues.add("value1");
        detailValues.add("value2");
        detailValues.add("value3");
        detailValues.add("value4");

        HorizontalRowAdapter consultationDetailsAdapter = new HorizontalRowAdapter(this, detailNames, detailValues);
        ListView consultationDetailsList = findViewById(R.id.list_view);
        consultationDetailsList.setAdapter(consultationDetailsAdapter);

    }
}
