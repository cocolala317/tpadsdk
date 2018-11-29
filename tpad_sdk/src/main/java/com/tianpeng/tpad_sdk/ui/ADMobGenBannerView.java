package com.tianpeng.tpad_sdk.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tianpeng.tpad_sdk.data.ADResponseBean;
import com.tianpeng.tpad_sdk.listener.ADRevertListener;
import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;
import com.tianpeng.tpad_sdk.ui.activity.AdDetailActivity;
import com.tianpeng.tpad_sdk.utils.ParamUtil;
import com.tianpeng.tpad_sdk.utils.TPLogger;
import com.tianpeng.tpad_sdk.utils.WhType;

/**
 * Created by YuHong on 2018/11/29 0029.
 */
public final class ADMobGenBannerView extends ADInfoView<ADRevertListener, ADMobGenBannerView> implements ViewTreeObserver.OnScrollChangedListener {
    private Rect rect = new Rect();
    private long d;
    private int height;
    private int width;

    public ADMobGenBannerView(Activity var1) {
        super(var1, 1001);
        this.setMinimumHeight((int)(this.getResources().getDisplayMetrics().density * 50.0F));
    }

    public void setADSize(int height, int width) {
        if (height >= 0 && width >= 0) {
            this.width = width;
            this.height = height;
        }
    }

    public int getTTheight() {
        return this.height;
    }

    public int getTTwidth() {
        return this.width;
    }

    public void setListener(ADRevertListener listener) {
        super.setListener(listener);
    }

    public final ADMobGenBannerView getParam() {
        return this;
    }

    public ADRevertListener getListener() {
        return super.getListener();
    }

    protected void onMeasure(int var1, int var2) {
        this.setMeasuredDimension(getDefaultSize(0, var1), getDefaultSize(0, var2));
        int var3 = this.getMeasuredWidth();
        var1 = MeasureSpec.makeMeasureSpec(var3, MeasureSpec.EXACTLY);
        super.onMeasure(var1, var2);
    }

    public final void loadAd() {
        this.removeScorllChangedListener();
        this.addScorllChangedListener();
        if (this.getViewTreeObserver() != null) {
            this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int var1 = ADMobGenBannerView.this.getHeight();
                    int var2 = ADMobGenBannerView.this.getWidth();
                    if (var1 > 0 && var2 > 0) {
                        ADMobGenBannerView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        ADMobGenBannerView.this.loadBanner(false);
                    }

                }
            });
        }

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
        this.setListener((ADRevertListener) null);
        this.removeScorllChangedListener();
        super.destroy();
    }

    public void onScrollChanged() {
        this.loadBanner(true);
    }

    private void addScorllChangedListener() {
        if (this.getViewTreeObserver() != null) {
            this.getViewTreeObserver().addOnScrollChangedListener(this);
        }

    }

    private void removeScorllChangedListener() {
        if (this.getViewTreeObserver() != null) {
            this.getViewTreeObserver().removeOnScrollChangedListener(this);
        }

    }

    private void loadBanner(boolean var1) {
        if (!this.isDestroy()) {
            long var2 = System.currentTimeMillis();
            if (var2 - this.d >= 100L || !var1) {
                this.d = var2;
                rect.set(0, 0, 0, 0);
                this.getLocalVisibleRect(rect);
                int var4 = this.getMeasuredWidth();
                int var5 = this.getMeasuredHeight();
                if (var4 > 0 && var5 > 0) {
                    int var6 = rect.right - rect.left;
                    int var7 = rect.bottom - rect.top;
                    if (rect.left == 0 && var6 >= var4 / 2 && rect.top == 0 && var7 >= var5 / 2) {
                        this.removeScorllChangedListener();
                        super.loadBannerAD(width,height);
                    }

                }
            }
        }
    }
}

