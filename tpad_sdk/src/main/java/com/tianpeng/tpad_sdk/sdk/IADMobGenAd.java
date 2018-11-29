package com.tianpeng.tpad_sdk.sdk;

import android.app.Activity;
import android.content.Context;

import com.tianpeng.tpad_sdk.listener.ADRevertListener;


/**
 * Created by YuHong on 2018/11/26 0026.
 */
public interface IADMobGenAd<T extends ADRevertListener, E extends IADMobGenAd> {
    void loadAd();

    void destroy();

    boolean isDestroy();

    Activity getActivity();

    Context getApplicationContext();

    T getListener();

    void setListener(T var1);

    E getParam();
}

