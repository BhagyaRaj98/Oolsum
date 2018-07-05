package com.example.bhagy.oolsum.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bhagy.oolsum.activities.MainScreenActivity;
import com.example.bhagy.oolsum.enums.RestServiceHelper;
import com.example.bhagy.oolsum.enums.SecurityRepository;
import com.example.bhagy.oolsum.interfaces.OolsumFragmentInteractionListener;
import com.example.bhagy.oolsum.objects.Realm;
import com.example.bhagy.oolsum.objects.Token;
import com.example.bhagy.oolsum.utils.PrefManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.example.bhagy.oolsum.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OolsumFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginEmailFragment extends Fragment {
    private static final String TAG = "LoginEmailFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OolsumFragmentInteractionListener mListener;

    private SecurityRepository securityRepository = SecurityRepository.INSTANCE;
    private RestServiceHelper serviceHelper = RestServiceHelper.INSTANCE;

    public LoginEmailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginEmailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginEmailFragment newInstance(String param1, String param2) {
        LoginEmailFragment fragment = new LoginEmailFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_email, container, false);
        Button btnBackToLoginOptions = view.findViewById(R.id.btnBackToLoginOptions);
        btnBackToLoginOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackToLoginOptionsClick();
            }
        });

        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmailLogin();
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

    public void onBackToLoginOptionsClick() {
        //Toast.makeText(this.getActivity(), "Clicked on Button", Toast.LENGTH_LONG).show();
        FragmentManager manager = this.getActivity().getSupportFragmentManager();
        Fragment fragment = new LoginOptionFragment();
        manager.beginTransaction().replace(R.id.fc_login_option, fragment).commit();
    }

    public void onEmailLogin() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Log.i(TAG, "onEmailLogin: User already logged in, aborting login.");
            return;
        }

        EditText txtEmailId = this.getActivity().findViewById(R.id.txtEmailId);
        EditText txtPassword = this.getActivity().findViewById(R.id.txtPasswd);
        Log.i(TAG, "onEmailLogin: " + txtEmailId.getText().toString() + "/" + txtPassword.getText().toString());
        final FragmentActivity parentActivity = this.getActivity();

        auth.signInWithEmailAndPassword(txtEmailId.getText().toString(), txtPassword.getText().toString()).addOnSuccessListener(this.getActivity(), new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.i(TAG, "onSuccess: User logged in - " + authResult.getUser().getEmail());
                authResult.getUser().getIdToken(false).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                    @Override
                    public void onSuccess(GetTokenResult getTokenResult) {
                        serviceHelper.getSecurityService().login(new Token(getTokenResult.getToken())).enqueue(new Callback<Realm>() {
                            @Override
                            public void onResponse(@NonNull Call<Realm> call, @NonNull Response<Realm> response) {
                                Realm realm = response.body();
                                securityRepository.saveRealm(realm, parentActivity);
                                // Intent dashboardIntent = new Intent(parentActivity, DashboardActivity.class);
                                Intent mainScreenIntent = new Intent(parentActivity, MainScreenActivity.class);
                                parentActivity.finish();
                                parentActivity.startActivity(mainScreenIntent);
                            }

                            @Override
                            public void onFailure(@NonNull Call<Realm> call, @NonNull Throwable t) {
                                Log.e(TAG, "onFailure: Backend login failed.", t);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, ">>>>>>>>>> onFailure: Unable to get token for login.", e);
                    }
                });

            }
        }).addOnFailureListener(this.getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: User login failed.");
                Toast.makeText(parentActivity, "Login failed, either email or password is incorrect", Toast.LENGTH_LONG).show();
            }
        });
    }

}
