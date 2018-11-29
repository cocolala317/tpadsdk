package com.tianpeng.tpad_sdk.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tianpeng.tpad_sdk.constant.InformationAdType;
import com.tianpeng.tpad_sdk.data.ADResponseBean;
import com.tianpeng.tpad_sdk.listener.ADRevertListener;
import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;
import com.tianpeng.tpad_sdk.ui.activity.AdDetailActivity;
import com.tianpeng.tpad_sdk.utils.ParamUtil;
import com.tianpeng.tpad_sdk.utils.TPLogger;
import com.tianpeng.tpad_sdk.utils.WhType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by YuHong on 2018/11/29 0029.
 */
public final class ADMobGenInformation extends ADInfoView<ADRevertListener, ADMobGenInformation> {
    private List<Integer> infoType;
    private int picHeight;

    public ADMobGenInformation(Activity var1) {
        super(var1, InformationAdType.INFO_IMAGE);
    }

    public ADMobGenInformation(Activity var1, Integer... var2) {
        this(var1);
        this.infoType = Arrays.asList(var2);
    }

    public List<Integer> getInformationAdType() {
        return this.infoType;
    }

    public int getOnlyImageHeight() {
        return this.picHeight;
    }

    public void setOnlyImageHeight(int var1) {
        this.picHeight = var1;
    }

    public void setListener(ADRevertListener var1) {
        super.setListener(var1);
    }

    public ADMobGenInformation getParam() {
        return this;
    }

    public ADRevertListener getListener() {
        return (ADRevertListener) super.getListener();
    }

    public final void loadAd() {
        super.loadInfomationAD(infoType,picHeight);
    }

    public final boolean isDestroy() {
        return super.isDestroy();
    }

    public final Activity getActivity() {
        return super.getActivity();
    }

    @Override
    protected void initADInfo(final Context context, final ADResponseBean adResponseBean) {
        ImageView imageView = new ImageView(TPengADGenSDK.getInstance().getAdSdkContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getListener().onADClick();
                context.startActivity(new Intent(context, AdDetailActivity.class).putExtra("goodsUrl",adResponseBean.getAds().get(0).getLanding_url()));
            }
        });
        ParamUtil.rl_param(imageView, WhType.match, WhType.match);

        if(info.getAds()!=null&&info.getAds().size()>0&&info.getAds().get(0).getImage_url()!=null&&info.getAds().get(0).getImage_url().size()>0) {
            Picasso.with(context).load(info.getAds().get(0).getImage_url().get(0)).error(android.R.mipmap.sym_def_app_icon).into(imageView);
            getListener().onADExposure();
            this.addView(imageView);
        }else {
            TPLogger.e("no ads data");
        }
    }

    public void destroy() {
        super.destroy();
        this.setListener((ADRevertListener) null);
    }
}

