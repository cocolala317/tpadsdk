package com.tianpeng.tpad_sdk.listener;

/**
 * Created by YuHong on 2018/11/14 0014.
 */
public interface ADRevertListener {
    void onADFailed(String var1);

    void onADReceiv();

    void onADClick();

    void onAdClose();

    void onADExposure();
}
