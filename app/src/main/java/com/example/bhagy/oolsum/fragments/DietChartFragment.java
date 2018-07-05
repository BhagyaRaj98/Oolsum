package com.example.bhagy.oolsum.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bhagy.oolsum.adapters.DietChartAdapter;
import com.example.bhagy.oolsum.objects.AssignedDietChart;

import java.util.ArrayList;
import java.util.List;

import com.example.bhagy.oolsum.R;

public class DietChartFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView tv_planningdays,tv_assignedbyvalue;
    Spinner spn_planningdays;
    RecyclerView recycler_dietchart;
    View rootView;

    ArrayList<String> days=new ArrayList<>();
    List<AssignedDietChart> dietchartList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.user_home_with_diet_chart_fragment, container, false);

        findViews();

        tv_planningdays.setText("Planning For 3 Days");
        days.add("Select Days");
        days.add("1st day");
        days.add("2nd day");
        days.add("3rd day");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, days);
        dataAdapter.setDropDownViewResource (android.R.layout.simple_list_item_single_choice);
        spn_planningdays.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        spn_planningdays.setAdapter(dataAdapter);

         dietchartList = new ArrayList<>();

        /*for (int i = 0; i < 5; i++) {
            dietchartList.add(new AssignedDietChart(R.drawable.logo, "Breakfast", "07:20 AM","10:25 AM","asdadsdasd"));
            dietchartList.add(new AssignedDietChart(R.drawable.logo, "Lunch", "01:30 PM","02:00 PM","sdfgsdfg"));
        }*/

        return rootView;
    }

    public void findViews() {
        tv_planningdays =  rootView.findViewById(R.id.tv_planning_days);
        tv_assignedbyvalue = rootView.findViewById(R.id.tv_assigned_by_value);
        spn_planningdays = rootView.findViewById(R.id.spn_planning_days);
        recycler_dietchart=rootView.findViewById(R.id.recycler_diet_chart);

        spn_planningdays.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView==spn_planningdays){
            if(spn_planningdays.getSelectedItemPosition()-1==0){
                dietchartList.clear();
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Breakfast", "07:20 AM","10:25 AM","asdadsdasd"));
            }else if(spn_planningdays.getSelectedItemPosition()-1==1){
                dietchartList.clear();
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Breakfast", "07:20 AM","10:25 AM","asdadsdasd"));
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Lunch", "01:30 PM","02:00 PM","sdfgsdfg"));
            }else if(spn_planningdays.getSelectedItemPosition()-1==2){
                dietchartList.clear();
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Breakfast", "07:20 AM","10:25 AM","asdadsdasd"));
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Lunch", "01:30 PM","02:00 PM","sdfgsdfg"));
                dietchartList.add(new AssignedDietChart(R.drawable.info, "Dinner", "07:30 PM","09:00 PM","dghgfhghhgj"));
            }
        }
        DietChartAdapter adapter = new DietChartAdapter(getContext(),dietchartList);
        recycler_dietchart.setAdapter(adapter);
        recycler_dietchart.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
