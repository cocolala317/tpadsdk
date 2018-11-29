package com.tianpeng.tpad_sdk.utils;

import android.content.Context;
import android.view.ViewGroup;

import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;

/**
 * Created by YuHong on 2018/11/27 0027.
 */
public class WhType {
    public static final WhType match = new WhType(ViewGroup.LayoutParams.MATCH_PARENT);
    public static final WhType wrap = new WhType(ViewGroup.LayoutParams.WRAP_CONTENT);

    public static WhType dp(int size) {
        return new WhType((int) dp2px(TPengADGenSDK.getInstance().getAdSdkContext(), size));
    }

    int size = 0;

    public WhType(int size) {
        this.size = size;
    }

    static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}