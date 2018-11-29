package com.tianpeng.tpad_sdk.utils;

import android.util.Log;

/**
 * Created by YuHong on 2018/11/27 0027.
 */
public class TPLogger {

    public TPLogger() {
    }

    public static void d(String var0) {
    }

    public static void i(String var0) {
        Log.i("tp_ad_mob", var0);
    }

    public static void e(String var0, Throwable var1) {
        if (var1 == null) {
            Log.e("tp_ad_mob", var0);
        } else {
            Log.e("tp_ad_mob", var0, var1);
        }
    }

    public static void w(String var0, Throwable var1) {
        if (var1 == null) {
            Log.w("tp_ad_mob", var0);
        } else {
            Log.w("tp_ad_mob", var0, var1);
        }
    }

    public static void w(String var0) {
        Log.e("tp_ad_mob", var0);
    }

    public static void e(String var0) {
        Log.e("tp_ad_mob", var0);
    }

}
