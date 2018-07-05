package com.example.bhagy.oolsum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bhagy.oolsum.R;

public class InstructionsFragment extends Fragment {

    EditText et_generalinstructions,et_foodstoavoid,et_foodstoconsumeless,et_fitnessinstructions,et_dietarysupplement;
    TextView tv_assignedvalue;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView  = inflater.inflate(R.layout.user_home_instructions_fragment, container, false);

        findViews();

        return rootView;
    }

    public void findViews(){
        et_generalinstructions = (EditText) rootView.findViewById(R.id.et_generalinstructions);
        et_foodstoavoid = (EditText) rootView.findViewById(R.id.et_foodstoavoid);
        et_foodstoconsumeless = (EditText) rootView.findViewById(R.id.et_foodstoconsumeless);
        et_fitnessinstructions = (EditText) rootView.findViewById(R.id.et_fitnessinstructions);
        et_dietarysupplement = (EditText) rootView.findViewById(R.id.et_dietarysupplement);
        tv_assignedvalue=(TextView)rootView.findViewById(R.id.tv_assignedvalue);
    }
}
