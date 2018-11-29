package com.tianpeng.tpad_sdk.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by YuHong on 2018/11/28 0028.
 */
public class VideoWebChromeClient extends WebChromeClient implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private View mWebViewContainer;
    private ViewGroup mFullScreenContainer;
    private View loadingView;
    private boolean isVideoFullscreen;
    private FrameLayout videoViewContainer;
    private CustomViewCallback videoViewCallback;
    private VideoWebChromeClient.ToggledFullscreenCallback toggledFullscreenCallback;

    public VideoWebChromeClient() {
    }

    public VideoWebChromeClient(View var1, ViewGroup var2, Context var3) {
        this.mWebViewContainer = var1;
        this.mFullScreenContainer = var2;
        this.loadingView = null;
        this.isVideoFullscreen = false;
        this.loadingView = new ProgressBar(var3);
    }

    public boolean isVideoFullscreen() {
        return this.isVideoFullscreen;
    }

    public void setOnToggledFullscreen(VideoWebChromeClient.ToggledFullscreenCallback var1) {
        this.toggledFullscreenCallback = var1;
    }

    public void onShowCustomView(View var1, CustomViewCallback var2) {
        if (var1 instanceof FrameLayout) {
            FrameLayout var3 = (FrameLayout)var1;
            View var4 = var3.getFocusedChild();
            this.isVideoFullscreen = true;
            this.videoViewContainer = var3;
            this.videoViewCallback = var2;
            this.mWebViewContainer.setVisibility(View.INVISIBLE);
            this.mFullScreenContainer.addView(this.videoViewContainer, new ViewGroup.LayoutParams(-1, -1));
            this.mFullScreenContainer.setVisibility(View.VISIBLE);
            if (var4 instanceof VideoView) {
                VideoView var5 = (VideoView)var4;
                var5.setOnPreparedListener(this);
                var5.setOnCompletionListener(this);
                var5.setOnErrorListener(this);
            }

            if (this.toggledFullscreenCallback != null) {
                this.toggledFullscreenCallback.toggledFullscreen(true);
            }
        }

    }

    public void onShowCustomView(View var1, int var2, CustomViewCallback var3) {
        this.onShowCustomView(var1, var3);
    }

    public void onHideCustomView() {
        if (this.isVideoFullscreen) {
            this.mFullScreenContainer.setVisibility(View.INVISIBLE);
            this.mFullScreenContainer.removeView(this.videoViewContainer);
            this.mWebViewContainer.setVisibility(View.VISIBLE);
            if (this.videoViewCallback != null && !this.videoViewCallback.getClass().getName().contains(".chromium.")) {
                this.videoViewCallback.onCustomViewHidden();
            }

            this.isVideoFullscreen = false;
            this.videoViewContainer = null;
            this.videoViewCallback = null;
            if (this.toggledFullscreenCallback != null) {
                this.toggledFullscreenCallback.toggledFullscreen(false);
            }
        }

    }

    public View getVideoLoadingProgressView() {
        if (this.loadingView != null) {
            this.loadingView.setVisibility(View.VISIBLE);
            return this.loadingView;
        } else {
            return super.getVideoLoadingProgressView();
        }
    }

    public void onReceivedTitle(WebView var1, String var2) {
        super.onReceivedTitle(var1, var2);
        this.toggledFullscreenCallback.getTitle(var2);
    }

    public void onPrepared(MediaPlayer var1) {
        if (this.loadingView != null) {
            this.loadingView.setVisibility(View.GONE);
        }

    }

    public void onCompletion(MediaPlayer var1) {
        this.onHideCustomView();
    }

    public boolean onError(MediaPlayer var1, int var2, int var3) {
        return false;
    }

    public boolean onBackPressed() {
        if (this.isVideoFullscreen) {
            this.onHideCustomView();
            return true;
        } else {
            return false;
        }
    }

    public interface ToggledFullscreenCallback {
        void toggledFullscreen(boolean var1);

        void getTitle(String var1);
    }
}
