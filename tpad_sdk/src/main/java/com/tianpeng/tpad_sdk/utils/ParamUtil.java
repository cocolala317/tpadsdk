package com.tianpeng.tpad_sdk.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tianpeng.tpad_sdk.sdk.TPengADGenSDK;

/**
 * Created by YuHong on 2018/11/27 0027.
 */
public class ParamUtil {
    View view;
    LayoutType type;
    ViewGroup.LayoutParams params;

    WhType width;
    WhType height;
    private float weight;

    private int margin;
    private int marginLeft;
    private int marginRight;
    private int marginTop;
    private int marginBottom;

    private int padding;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    public static ParamUtil ll_param(View view, WhType width, WhType height) {
        return new ParamUtil(LayoutType.linear, view, width, height);
    }

    public static ParamUtil fl_param(View view, WhType width, WhType height) {
        return new ParamUtil(LayoutType.frame, view, width, height);
    }

    public static ParamUtil rl_param(View view, WhType width, WhType height) {
        return new ParamUtil(LayoutType.relate, view, width, height);
    }

    public ParamUtil(LayoutType type, View view, WhType width, WhType height) {
        this.type = type;
        this.view = view;
        this.width = width;
        this.height = height;
        switch (type) {
            case linear:
                params = new LinearLayout.LayoutParams(
                        width.size,
                        height.size
                );
                break;
            case relate:
                params = new RelativeLayout.LayoutParams(
                        width.size,
                        height.size
                );
                break;
            case frame:
                params = new FrameLayout.LayoutParams(
                        width.size,
                        height.size
                );
                break;
        }
        view.setLayoutParams(params);
    }

    //只有LinearLayout才有
    public ParamUtil weight(float weight) {
        this.weight = weight;
        if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).weight = weight;
        }
        return this;
    }


    //只有RelativeLayout才有
    public ParamUtil centerInParent() {
        if (params instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) params).addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        }
        return this;
    }
    //只有RelativeLayout才有
    public ParamUtil rightInParent() {
        if (params instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) params).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        }
        return this;
    }

    private void setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingRight = right;
        this.paddingTop = top;
        this.paddingBottom = bottom;
        view.setPadding((int) dp2px(left), (int) dp2px(top), (int) dp2px(right), (int) dp2px(bottom));
    }

    private void setMargin(int left, int top, int right, int bottom) {
        this.marginLeft = left;
        this.marginRight = top;
        this.marginTop = right;
        this.marginBottom = bottom;
        if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params)
                    .setMargins((int) dp2px(left), (int) dp2px(top), (int) dp2px(right), (int) dp2px(bottom));
        } else if (params instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) params)
                    .setMargins((int) dp2px(left), (int) dp2px(top), (int) dp2px(right), (int) dp2px(bottom));
        } else if (params instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) params)
                    .setMargins((int) dp2px(left), (int) dp2px(top), (int) dp2px(right), (int) dp2px(bottom));
        }
    }

    public ParamUtil margin(int margin) {
        this.margin = margin;
        setMargin(margin, margin, margin, margin);
        view.setLayoutParams(params);
        return this;
    }

    public ParamUtil marginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        setMargin(marginLeft, marginTop, marginRight, marginBottom);
        view.setLayoutParams(params);
        return this;
    }

    public ParamUtil marginRight(int marginRight) {
        this.marginRight = marginRight;
        setMargin(marginLeft, marginTop, marginRight, marginBottom);
        view.setLayoutParams(params);
        return this;
    }

    public ParamUtil marginTop(int marginTop) {
        this.marginTop = marginTop;
        setMargin(marginLeft, marginTop, marginRight, marginBottom);
        view.setLayoutParams(params);
        return this;
    }

    public ParamUtil marginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        setMargin(marginLeft, marginTop, marginRight, marginBottom);
        view.setLayoutParams(params);
        return this;
    }

    public ParamUtil padding(int padding) {
        this.padding = padding;
        view.setPadding(padding, padding, padding, padding);
        return this;
    }

    public ParamUtil paddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return this;
    }

    public ParamUtil paddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return this;
    }

    public ParamUtil paddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return this;
    }

    public ParamUtil paddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return this;
    }

    private float dp2px(float dp) {
        final float scale = TPengADGenSDK.getInstance().getAdSdkContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
