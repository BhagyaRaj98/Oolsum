package com.example.bhagy.oolsum.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bhagy.oolsum.enums.LoginEvent;
import com.example.bhagy.oolsum.interfaces.OolsumFragmentInteractionListener;
import com.example.bhagy.oolsum.R;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OolsumFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginOptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginOptionFragment extends Fragment {

    private static final String TAG = "LoginOptionFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OolsumFragmentInteractionListener mListener;

    public LoginOptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginOptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginOptionFragment newInstance(String param1, String param2) {
        LoginOptionFragment fragment = new LoginOptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_option, container, false);
        Button btnLoginUsingEmail = view.findViewById(R.id.btn_login_email);
        btnLoginUsingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginUsingEmailClick(view);
            }
        });

        Button btnLoginUsingGoogle = view.findViewById(R.id.btn_login_google);
        btnLoginUsingGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginUsingGoogleClick(view);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OolsumFragmentInteractionListener) {
            mListener = (OolsumFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onLoginUsingEmailClick(View v) {
        //Toast.makeText(this.getActivity(), "Clicked on Button", Toast.LENGTH_LONG).show();
        FragmentManager manager = this.getActivity().getSupportFragmentManager();
        Fragment fragment = new LoginEmailFragment();
        manager.beginTransaction().replace(R.id.fc_login_option, fragment).commit();
    }

    public void onLoginUsingGoogleClick(View v) {
        Log.i(TAG, "onLoginUsingGoogleClick: starting.");
        EventBus.getDefault().post(LoginEvent.GOOGLE);
    }
}
