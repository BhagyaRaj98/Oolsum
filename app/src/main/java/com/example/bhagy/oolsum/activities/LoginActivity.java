package com.example.bhagy.oolsum.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.enums.LoginEvent;
import com.example.bhagy.oolsum.enums.ProgressDialogHelper;
import com.example.bhagy.oolsum.enums.RestServiceHelper;
import com.example.bhagy.oolsum.enums.SecurityRepository;
import com.example.bhagy.oolsum.fragments.LoginOptionFragment;
import com.example.bhagy.oolsum.interfaces.OolsumFragmentInteractionListener;
import com.example.bhagy.oolsum.objects.Realm;
import com.example.bhagy.oolsum.objects.Token;
import com.example.bhagy.oolsum.objects.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;

public class LoginActivity extends AppCompatActivity implements OolsumFragmentInteractionListener {
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    private ProgressDialogHelper dialogHelper = ProgressDialogHelper.INSTANCE;
    private RestServiceHelper serviceHelper = RestServiceHelper.INSTANCE;
    private SecurityRepository securityRepository = SecurityRepository.INSTANCE;

    private ProgressDialog progressDialog;

    private GoogleSignInClient googleSignInClient;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = dialogHelper.getIndeterminateDialog(this, "Login in progress...");

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fc_login_option);

        if (fragment == null)
            fragment = LoginOptionFragment.newInstance(null, null);

        // Note: Don't use add, use replace, as add will crash app when orientation change.
        manager.beginTransaction().replace(R.id.fc_login_option, fragment).commit();

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.oolsum_only_word_white);

        initGoogleSignInClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult: " + requestCode + ":" + resultCode);

        if (RC_SIGN_IN == requestCode) {
            dialogHelper.show(progressDialog);
            GoogleSignIn.getSignedInAccountFromIntent(data).addOnSuccessListener(this, new OnSuccessListener<GoogleSignInAccount>() {
                @Override
                public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                    Log.i(TAG, "onSuccess: Google account login done.");
                    fireBaseAuthWithGoogle(googleSignInAccount);
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(TAG, "onFailure: Google account login fail.");
                    dialogHelper.dismiss(progressDialog);
                }
            });
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        if (LoginEvent.GOOGLE == event) {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    private void initGoogleSignInClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void fireBaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.i(TAG, "FireBaseAuthWithGoogle: now trying FireBase login. " + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithCredential(credential).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(final AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                user.getIdToken(false).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                    @Override
                    public void onSuccess(GetTokenResult getTokenResult) {
                        initSystemLogin(authResult, getTokenResult.getToken());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialogHelper.dismiss(progressDialog);
                        Log.i(TAG, ">>>>>>>>> onFailure: Unable to get user JWT token to proceed with login.");
                    }
                });
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: FireBase login using google failed.");
                dialogHelper.dismiss(progressDialog);
            }
        });
    }

    private void initSystemLogin(final AuthResult authResult, final String token) {
        final String email = (String) authResult.getAdditionalUserInfo().getProfile().get("email");
        final FirebaseUser user = authResult.getUser();
        //TODO: if no email then request for email.
        //TODO: load fragment to get more info from user
        Call<String> response = serviceHelper.getUserPublicService().getProfileMatch(user.getUid(), email);
        final FragmentActivity activity = this;

        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.i(TAG, "onResponse: " + response.body());
                Toast.makeText(activity, "Get profile info response: " + response.body(), Toast.LENGTH_LONG).show();
                String matchResult = response.body();

                if ("NONE".equals(matchResult)) {
                    User user = prepareUser(authResult);
                    serviceHelper.getUserPublicService().register(user).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            dialogHelper.dismiss(progressDialog);
                            //TODO: load fragment to get more info from user
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                            Log.e(TAG, ">>>>>>>>>> onFailure: User registration fail.", t);
                            dialogHelper.dismiss(progressDialog);
                        }
                    });

                    return;
                }

                if ("EMAIL_FOUND".equals(matchResult)) {
                    User user = prepareUser(authResult);
                    System.out.println("authResult====>"+authResult);
                    serviceHelper.getUserPublicService().register(user).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            doLogin(token);
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                            Log.e(TAG, ">>>>>>>>>> onFailure: User registration fail.", t);
                            dialogHelper.dismiss(progressDialog);
                        }
                    });

                    return;
                }

                doLogin(token);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                dialogHelper.dismiss(progressDialog);
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void doLogin(String token) {
        final FragmentActivity activity = this;
        serviceHelper.getSecurityService().login(new Token(token)).enqueue(new Callback<Realm>() {
            @Override

            public void onResponse(@NonNull Call<Realm> call, @NonNull Response<Realm> response) {

                securityRepository.saveRealm(response.body(), activity);
                System.out.println("response body====>"+response.body());
                dialogHelper.dismiss(progressDialog);
                Log.i(TAG, "onSuccess: FireBase login using google successful.");
//              Intent dashboardIntent = new Intent(activity, DashboardActivity.class);
                Intent mainScreenIntent = new Intent(activity, MainScreenActivity.class);
                activity.finish();
                activity.startActivity(mainScreenIntent);
            }

            @Override
            public void onFailure(@NonNull Call<Realm> call, @NonNull Throwable t) {
                dialogHelper.dismiss(progressDialog);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private User prepareUser(AuthResult authResult) {
        final String email = (String) authResult.getAdditionalUserInfo().getProfile().get("email");
        final FirebaseUser user = authResult.getUser();

        return new User().setUid(user.getUid()).setEmailId(email).setDisplayName(user.getDisplayName())
                .setEmailVerified(user.isEmailVerified()).setMobileNumber(user.getPhoneNumber())
                .setProfilePictureUrl(requireNonNull(user.getPhotoUrl()).toString()).setProviderId(user.getProviderId())
                .setRegistrationMethod("");
    }
}
