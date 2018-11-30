package com.tianpeng.tpad_sdk.listener;

import com.tianpeng.tpad_sdk.sdk.IADMobGenInformation;

/**
 * Created by YuHong on 2018/11/29 0029.
 */
public interface ADInfomationListener extends ADRevertListener {
    void onRecivData(IADMobGenInformation info);
}
