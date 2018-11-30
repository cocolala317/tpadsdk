package com.tianpeng.tpad_sdk.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tianpeng.tpad_sdk.constant.InformationAdType;
import com.tianpeng.tpad_sdk.data.ADResponseBean;
import com.tianpeng.tpad_sdk.data.GetADParam;
import com.tianpeng.tpad_sdk.listener.ADRevertListener;
import com.tianpeng.tpad_sdk.net.HttpManager;
import com.tianpeng.tpad_sdk.sdk.IADMobGenAd;
import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YuHong on 2018/11/26 0026.
 */
public abstract class ADInfoView<T extends ADRevertListener, E extends IADMobGenAd> extends RelativeLayout implements IADMobGenAd<T, E> {
    private final int height;
    private final Context context;
    protected ADResponseBean info;
    private T listener;
    protected final Activity activity;
    private boolean isInit;
    protected ADInfoView(Activity var1, int var2) {
        super(var1);
        this.height = var2;
        this.activity = var1;
        this.context = var1.getApplicationContext();
    }

    public void loadSplashAD(double f) {
        if (this.info == null) {
            GetADParam param = TPengADGenSDK.getInstance().getRequestParam();
            param.setPid(TPengADGenSDK.getInstance().getSplashPid());
            GetADParam.AdsBean adsBean = new GetADParam.AdsBean();
            adsBean.setType(InformationAdType.SPLASH);
            adsBean.setW(param.getDevice().getSw());
            adsBean.setH((int)((double)(param.getDevice().getSh())*f));
            List<GetADParam.AdsBean> list = new ArrayList<>();
            list.add(adsBean);
            param.setAds(list);
            HttpManager.requestADInfo(context,param,new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==HttpManager.SUCCESS){
                        Gson gson = new Gson();
                        info = gson.fromJson(msg.obj.toString(),ADResponseBean.class);
                        if(info.getCode()==0) {
                            if (listener != null)
                                listener.onADReceiv();
                           initADInfo(context,info);
                        }else {
                            if (listener != null)
                                listener.onADFailed(info.getMsg());
                        }
                    }else {
                        if (listener != null)
                            listener.onADFailed(msg.obj.toString());
                    }
                }
            });

        }

    }
    public void loadBannerAD(int width,int height) {
        if (this.info == null) {
            GetADParam param = TPengADGenSDK.getInstance().getRequestParam();
            param.setPid(TPengADGenSDK.getInstance().getBannerPid());
            GetADParam.AdsBean adsBean = new GetADParam.AdsBean();
            adsBean.setType(InformationAdType.BANNER);
            adsBean.setW(width==0?640:width);
            adsBean.setH(height==0?100:height);
            List<GetADParam.AdsBean> list = new ArrayList<>();
            list.add(adsBean);
            param.setAds(list);
            HttpManager.requestADInfo(context,param,new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==HttpManager.SUCCESS){
                        Gson gson = new Gson();
                        info = gson.fromJson(msg.obj.toString(),ADResponseBean.class);
                        if(info.getCode()==0) {
                            if (listener != null)
                                listener.onADReceiv();
                           initADInfo(context,info);
                        }else {
                            if (listener != null)
                                listener.onADFailed(info.getMsg());
                        }
                    }else {
                        if (listener != null)
                            listener.onADFailed(msg.obj.toString());
                    }
                }
            });

        }

    }
    public void loadInfomationAD(List<Integer> types,int height) {
        if (this.info == null) {
            GetADParam param = TPengADGenSDK.getInstance().getRequestParam();
            param.setPid(TPengADGenSDK.getInstance().getBannerPid());
            GetADParam.AdsBean adsBean = new GetADParam.AdsBean();
            adsBean.setType(InformationAdType.BANNER);
            adsBean.setW(param.getDevice().getSw());
            adsBean.setH(height==0?100:height);
            adsBean.setInventory_types(types);
            List<GetADParam.AdsBean> list = new ArrayList<>();
            list.add(adsBean);
            param.setAds(list);
            HttpManager.requestADInfo(context,param,new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==HttpManager.SUCCESS){
                        Gson gson = new Gson();
                        info = gson.fromJson(msg.obj.toString(),ADResponseBean.class);
                        if(info.getCode()==0) {
                           initADInfo(context,info);
                        }else {
                            if (listener != null)
                                listener.onADFailed(info.getMsg());
                        }
                    }else {
                        if (listener != null)
                            listener.onADFailed(msg.obj.toString());
                    }
                }
            });

        }

    }

    protected abstract void initADInfo(Context context,ADResponseBean adResponseBean);

    public void destroy() {
        this.isInit = true;
        if (this.info != null) {
//            this.info.a();
            this.info = null;
        }

    }

    public boolean isDestroy() {
        return this.isInit || this.activity == null || this.activity.isFinishing();
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Context getApplicationContext() {
        return this.activity;
    }

    public T getListener() {
        return this.listener;
    }

    public void setListener(T var1) {
        this.listener = var1;
    }
}
