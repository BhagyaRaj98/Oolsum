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
import com.example.bhagy.oolsum.adapters.IndependentAdapter;
import com.example.bhagy.oolsum.objects.Independent;

import java.util.ArrayList;
import java.util.List;

public class IndependentsFragment extends Fragment {

    public IndependentsFragment() {
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
        List<Independent> independents = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            independents.add(new Independent("offline", "4.0", R.drawable.ic_user, "ASDFG", "M.sc Nutrition",
                    "7 years Experience", "Diabetic", "300"));
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.category_recycler_list);
        IndependentAdapter independentAdapter = new IndependentAdapter(getContext(), independents);

        recyclerView.setAdapter(independentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
