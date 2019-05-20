package com.amazonaws.mobile.samples.mynotes.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.auth.google.GoogleButton;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.HostedUIOptions;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.samples.mynotes.R;


public class AuthenticatorActivity_Google extends Activity{

    private static final String TAG = "Auth Google";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator__google);

        // Add a call to initialize AWSMobileClient
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(AuthenticatorActivity_Google.this, SignInUI.class);

                signin.login(AuthenticatorActivity_Google.this, NoteListActivity.class).execute();

                // For Google
                HostedUIOptions hostedUIOptions = HostedUIOptions.builder()
                        .scopes("openid", "email")
                        .identityProvider("Google")
                        .build();

                SignInUIOptions signInUIOptions = SignInUIOptions.builder()
                        .hostedUIOptions(hostedUIOptions)
                        .nextActivity(NoteListActivity.class)
                        .build();

                // 'this' refers to the current active Activity
                AWSMobileClient.getInstance().showSignIn(AuthenticatorActivity_Google.this, signInUIOptions, new Callback<UserStateDetails>() {
                    @Override
                    public void onResult(UserStateDetails details) {
                        Log.d(TAG, "onResult: " + details.getUserState());
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });

            }
        }).execute();




    }
}
