package com.alexpoltavets.fifacommunity.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alexpoltavets.fifacommunity.R;
import com.alexpoltavets.fifacommunity.main.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager; // FB
    private VKCallback<VKAccessToken> callback; //VK
    private Profile facebookProfile;

    private static final String[] vk_skope=new String[]{
            VKScope.FRIENDS,
            VKScope.GROUPS,
            VKScope.STATUS,
            VKScope.PHOTOS,
    };

    public static final String FACEBOOK_PROFILE_KEY="facebookprofile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFacebookSdk();
        setContentView(R.layout.login_activity);
        initFacebookLogin();
        initVKLogin();
    }

    private void setupFacebookSdk(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
    }

    private void initFacebookLogin() {
        LoginButton facebookBtn = (LoginButton) findViewById(R.id.login_facebook_btn);
        facebookBtn.setReadPermissions();
        callbackManager = CallbackManager.Factory.create();
        facebookBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {{
                    if(Profile.getCurrentProfile()==null) {
                        ProfileTracker tracker=new ProfileTracker(){
                            @Override
                            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                facebookProfile=currentProfile;
                                startMainActivity();
                            }
                        };
                    }
                    else{
                        facebookProfile=Profile.getCurrentProfile();
                        startMainActivity();
                    }
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(), "FAILED!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getBaseContext(), "FAILED!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initVKLogin(){
        Button vkBtn= (Button) findViewById(R.id.login_vk_btn);
        vkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.login(LoginActivity.this,vk_skope);
            }
        });
         callback= new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                Log.d("LOG","SUSCCESS");
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                Log.d("LOG","ERROR");
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(callbackManager.onActivityResult(requestCode, resultCode, data)){
        //FB Logined
        }
        else if(VKSdk.onActivityResult(requestCode,resultCode,data,callback)){
        //VK Logined
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void startMainActivity(){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra(FACEBOOK_PROFILE_KEY,facebookProfile);
        startActivity(intent);
    }
}
