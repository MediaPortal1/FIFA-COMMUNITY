package com.alexpoltavets.fifacommunity.application;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by Alex Poltavets on 26.08.2016.
 */
public class FifaAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
