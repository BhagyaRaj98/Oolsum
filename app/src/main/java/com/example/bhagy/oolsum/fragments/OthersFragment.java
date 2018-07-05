package com.example.bhagy.oolsum.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.HospitalAdapter;
import com.example.bhagy.oolsum.objects.Hospital;

import java.util.ArrayList;
import java.util.List;

public class OthersFragment extends Fragment {
    public OthersFragment() {
        // Required empty public constructor
    }

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.consult_now_filter_fragments, container, false);
        setAdapter(rootView);

        return rootView;
    }

    public void setAdapter(View rootView) {
        List<Hospital> hospitals = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            hospitals.add(new Hospital.HospitalBuilder("Hospital 1", "Chennai", "Kids").addImage(R.drawable.hospital)
                    .addAverageCost("200").addDietitiansOnline("2").addRating("4.0").buildHospital());
            hospitals.add(new Hospital.HospitalBuilder("Hospital 2", "Bangalore", "Kids, Sports").addImage(R.drawable.hospital)
                    .addAverageCost("300").addDietitiansOnline("4").addRating("4.2").buildHospital());
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.category_recycler_list);
        HospitalAdapter otherAdapter = new HospitalAdapter(getContext(), hospitals);

        recyclerView.setAdapter(otherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
