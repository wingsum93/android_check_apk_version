package com.ericho.android.checkapkversion;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by EricH on 26/11/2015.
 * add dbflow
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

}
