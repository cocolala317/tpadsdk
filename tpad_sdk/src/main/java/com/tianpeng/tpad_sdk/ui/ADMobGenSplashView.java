package com.tianpeng.tpad_sdk.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tianpeng.tpad_sdk.R;
import com.tianpeng.tpad_sdk.data.ADResponseBean;
import com.tianpeng.tpad_sdk.listener.ADRevertListener;
import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;
import com.tianpeng.tpad_sdk.ui.activity.AdDetailActivity;
import com.tianpeng.tpad_sdk.utils.ParamUtil;
import com.tianpeng.tpad_sdk.utils.TPLogger;
import com.tianpeng.tpad_sdk.utils.WhType;

/**
 * Created by YuHong on 2018/11/27 0027.
 */
public class ADMobGenSplashView extends ADInfoView<ADRevertListener, ADMobGenSplashView> {
    private static double heightPixels = 0.75D;
    private final int height;

    public ADMobGenSplashView(Activity var1) {
        this(var1, heightPixels);
    }

    public ADMobGenSplashView(Activity var1, double var2) {
        super(var1, 1000);
        if (var2 >= heightPixels && var2 <= 1.0D) {
            heightPixels = var2;
        }

        this.height = (int) ((double) this.getResources().getDisplayMetrics().heightPixels * heightPixels);
        RelativeLayout.LayoutParams var4 = new RelativeLayout.LayoutParams(-1, this.height);
        this.setLayoutParams(var4);
    }

    protected void onMeasure(int var1, int var2) {
        this.setMeasuredDimension(getDefaultSize(0, var1), getDefaultSize(0, var2));
        int var3 = this.getMeasuredWidth();
        var1 = MeasureSpec.makeMeasureSpec(var3, MeasureSpec.EXACTLY);
        var2 = MeasureSpec.makeMeasureSpec(this.height, MeasureSpec.EXACTLY);
        super.onMeasure(var1, var2);
    }

    public void setListener(ADRevertListener var1) {
        super.setListener(var1);
    }

    public final ADMobGenSplashView getParam() {
        return this;
    }

    public ADRevertListener getListener() {
        return (ADRevertListener) super.getListener();
    }

    public final void loadAd() {
        super.loadSplashAD(heightPixels);
    }
    int index = 3;
    TextView textView;
    @Override
    protected void initADInfo(final Context context, final ADResponseBean bean) {
        ImageView imageView = new ImageView(TPengADGenSDK.getInstance().getAdSdkContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getListener().onADClick();
                context.startActivity(new Intent(context, AdDetailActivity.class)
                        .putExtra("goodsUrl",bean.getAds().get(0).getLanding_url()).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK ));
            }
        });
        textView = new TextView(TPengADGenSDK.getInstance().getAdSdkContext());
        textView.setText("跳过 "+index);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setBackgroundResource(R.drawable.bg_splash_tip);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getListener().onAdClose();
            }
        });
        ParamUtil.rl_param(imageView, WhType.match, WhType.match);
        ParamUtil.rl_param(textView, WhType.wrap, WhType.wrap).rightInParent().padding(10).margin(20);

        if(info.getAds()!=null&&info.getAds().size()>0&&info.getAds().get(0).getImage_url()!=null&&info.getAds().get(0).getImage_url().size()>0) {
            Picasso.with(context).load(info.getAds().get(0).getImage_url().get(0)).error(android.R.mipmap.sym_def_app_icon).into(imageView);
            getListener().onADExposure();
            this.addView(imageView);
            this.addView(textView);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(index>=1) {
                        index--;
                        textView.setText("跳过 "+index);
                        handler.postDelayed(this,1000);
                    }else {
                        getListener().onAdClose();
                    }

                }
            }, 1000);
        }else
            TPLogger.e("no ads data");


    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    public final boolean isDestroy() {
        return super.isDestroy();
    }

    public final Activity getActivity() {
        return super.getActivity();
    }

    public void destroy() {
        this.setListener((ADRevertListener) null);
        super.destroy();
        this.removeAllViews();
    }
}
