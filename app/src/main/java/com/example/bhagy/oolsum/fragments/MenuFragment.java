package com.example.bhagy.oolsum.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhagy.oolsum.activities.ConsultDietitianActivity;
import com.example.bhagy.oolsum.activities.ContactUsActivity;
import com.example.bhagy.oolsum.activities.LoginActivity;
import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.enums.RestServiceHelper;
import com.example.bhagy.oolsum.enums.SecurityRepository;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MenuFragment";
    private SecurityRepository securityRepository = SecurityRepository.INSTANCE;
    private RestServiceHelper serviceHelper = RestServiceHelper.INSTANCE;

    public MenuFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.user_menu_fragment, container, false);

        rootView.findViewById(R.id.ll_consult_dietitian).setOnClickListener(this);
        rootView.findViewById(R.id.ll_consultation_history).setOnClickListener(this);
        rootView.findViewById(R.id.ll_ongoing_consultation).setOnClickListener(this);
        rootView.findViewById(R.id.ll_contact_us).setOnClickListener(this);
        rootView.findViewById(R.id.ll_logout).setOnClickListener(this);

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void doSignOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        final FragmentActivity activity = getActivity();
        assert activity != null;
        serviceHelper.getSecurityService().logout(securityRepository.getToken(activity)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                securityRepository.removeRealm(activity);
                securityRepository.removeToken(activity);
                Intent loginIntent = new Intent(activity, LoginActivity.class);
                activity.finish();
                startActivity(loginIntent);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: Unable to logout from oolsum system.", t);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_consult_dietitian:
                Intent consultNowIntent = new Intent(getContext(), ConsultDietitianActivity.class);
                startActivity(consultNowIntent);
                break;
            case R.id.ll_consultation_history:
                break;

            case R.id.iv_ongoing_consultation:
                break;

            case R.id.ll_contact_us:
                Intent contactUsIntent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(contactUsIntent);

                break;
            case R.id.ll_logout:
                doSignOut();
                break;

        }
    }
}
