package com.example.bhagy.oolsum.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bhagy.oolsum.activities.EditProfileActivity;
import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.adapters.InfoAdapter;
import com.example.bhagy.oolsum.objects.InfoItem;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.user_profile_fragment, container, false);
        setProfileDetails(rootView);

        ImageView editIconView = rootView.findViewById(R.id.edit_icon);
        editIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(editProfileIntent);
            }
        });
        return rootView;

    }

    public void setProfileDetails(View rootView) {

        ArrayList<InfoItem> basicInfoList = new ArrayList<>();
        ArrayList<InfoItem> contactInfoList = new ArrayList<>();
        ArrayList<InfoItem> lifeStyleInfoList = new ArrayList<>();

        basicInfoList.add(new InfoItem(R.drawable.info, "User Name", "Quapt"));
        basicInfoList.add(new InfoItem(R.drawable.info, "Weight", "60.0 kg"));
        basicInfoList.add(new InfoItem(R.drawable.info, "Height", "185.00 cm"));
        basicInfoList.add(new InfoItem(R.drawable.info, "BMI", "17.5 Underweight"));
        basicInfoList.add(new InfoItem(R.drawable.info, "Date of Birth", "1998-04-17"));
        basicInfoList.add(new InfoItem(R.drawable.info, "Gender", "Male"));
        basicInfoList.add(new InfoItem(R.drawable.info, "Gender", "SINGLE"));

        contactInfoList.add(new InfoItem(R.drawable.info, "Email", "asdfg@mailinator.com"));
        contactInfoList.add(new InfoItem(R.drawable.info, "Mobile Number", "9876543210"));
        contactInfoList.add(new InfoItem(R.drawable.info, "Residence Phone", "7894561230"));
        contactInfoList.add(new InfoItem(R.drawable.info, "Address", "Srinivasapuram, Tirupati"));

        lifeStyleInfoList.add(new InfoItem(R.drawable.info, "Food Culture", ""));
        lifeStyleInfoList.add(new InfoItem(R.drawable.info, "Nature of Work", "6 years"));
        lifeStyleInfoList.add(new InfoItem(R.drawable.info, "Job Shift", "Day Shift"));
        lifeStyleInfoList.add(new InfoItem(R.drawable.info, "Goal", "Weight Loss"));
        lifeStyleInfoList.add(new InfoItem(R.drawable.info, "Medical Conditions", "Diabetes"));

        RecyclerView basicInfoRecyclerView = rootView.findViewById(R.id.basic_info_recycler_view);
        RecyclerView contactInfoRecyclerView = rootView.findViewById(R.id.contact_info_recycler_view);
        RecyclerView lifeStyleInfoRecyclerView = rootView.findViewById(R.id.lifestyle_info_recycler_view);

        setAdapter(basicInfoRecyclerView, basicInfoList);
        setAdapter(contactInfoRecyclerView, contactInfoList);
        setAdapter(lifeStyleInfoRecyclerView, lifeStyleInfoList);

    }

    public void setAdapter(RecyclerView infoRecyclerView, ArrayList<InfoItem> infoList) {
        InfoAdapter infoAdapter = new InfoAdapter(getContext(), infoList);
        infoRecyclerView.setAdapter(infoAdapter);
        infoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

}
