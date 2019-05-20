package com.amazonaws.mobile.samples.mynotes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;

import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.IdentityProvider;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.samples.mynotes.R;

public class AuthenticatorActivity_FB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authenticator__fb);
        // AccessToken accessToken = AccessToken.getCurrentAccessToken();

        AWSMobileClient.getInstance().initialize(this, new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails userStateDetails) {
                Log.i("INIT", userStateDetails.getUserState().toString());
                AWSMobileClient.getInstance().showSignIn(
                        AuthenticatorActivity_FB.this,
                        SignInUIOptions.builder()
                                .nextActivity(NoteListActivity.class)
                                .build(),
                        new Callback<UserStateDetails>() {
                            @Override
                            public void onResult(UserStateDetails result) {
                                Log.d("onResult: ", result.getUserState().toString());
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e("onError: ", e.toString());
                            }
                        }
                );
            }

            @Override
            public void onError(Exception e) {
                Log.e("INIT", "Error during initialization", e);
            }
        });
    }
}
