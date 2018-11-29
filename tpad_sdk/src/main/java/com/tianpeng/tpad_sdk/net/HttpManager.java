package com.tianpeng.tpad_sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.tianpeng.tpad_sdk.data.GetADParam;
import com.tianpeng.tpad_sdk.utils.TPLogger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class HttpManager {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    /**
     * post方式请求,异步
     */
    public static void requestADInfo(Context context, GetADParam map, final Handler handler) {
        if (!isNetworkAvailable(context)) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody
                .create(type,gson.toJson(map));
        Request request = new Request.Builder()
                .url("https://ad.tianpengnet.cn/ad/tianpeng/test")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Message message = new Message();
                message.what = 1;
                message.obj = "http error!!";
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                TPLogger.e(resStr);
                Message message = new Message();
                message.what = 0;
                message.obj = resStr;
                handler.sendMessage(message);
            }
        });
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
