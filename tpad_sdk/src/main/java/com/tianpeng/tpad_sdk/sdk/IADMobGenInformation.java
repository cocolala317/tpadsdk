package com.tianpeng.tpad_sdk.sdk;

import android.view.View;

/**
 * Created by YuHong on 2018/11/30 0030.
 */
public interface IADMobGenInformation {
    View getInformationAdView();

//    void render();
//
//    void onExposured();

    void destroy();

    boolean isDestroy();

    int getInformationAdType();
}