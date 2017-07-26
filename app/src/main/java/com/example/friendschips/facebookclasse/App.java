package com.example.friendschips.facebookclasse;

import android.app.Application;

import com.facebook.FacebookSdk;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by MaximeDrx on 26/07/2017.
 */

public class App  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
