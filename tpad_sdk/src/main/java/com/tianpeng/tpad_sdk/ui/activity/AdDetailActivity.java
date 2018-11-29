package com.tianpeng.tpad_sdk.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tianpeng.tpad_sdk.R;
import com.tianpeng.tpad_sdk.ui.VideoWebChromeClient;
import com.tianpeng.tpad_sdk.utils.MatchUtil;
import com.tianpeng.tpad_sdk.utils.TPLogger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YuHong on 2018/11/28 0028.
 */
public class AdDetailActivity extends FragmentActivity {
    FrameLayout layout_WebView;
    public WebView webView;
    Context mContext;
    public String url = "";
    private String tempreferer = "";
    private int RESULT_OK = -1;
    private String keyWord = "tmall,taobao,intent";
    private RelativeLayout backLayout;
    private RelativeLayout rlTitle;
    private TextView titleText;
    private FrameLayout video_fullView;
    private WebChromeClient myWebChromeClient;
    private RelativeLayout mTitleRl;
    private ProgressBar pbProgress;
    private TextView mCloseTv;
    private TextView mTitleTv;
    private String key = "";
    private String type = "";
    private AlertDialog permisionDialog;
    private AlertDialog downloadDialog;
//    private admsdk.library.b.a detailHelper;
    private String channel;
    private ValueCallback<Uri> mUploadFile;
    private ValueCallback<Uri[]> mFilePathCallback;
    private static final int REQUEST_UPLOAD_FILE_CODE = 12343;

    public AdDetailActivity() {
    }

    public void onCreate(@Nullable Bundle var1) {
        super.onCreate(var1);
        setContentView(R.layout.activity_admad_detail);
        mContext = this;
        getWindow().addFlags(16777216);//16777216
        url = getIntent().getStringExtra("goodsUrl");
        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");
        channel = getIntent().getStringExtra("channel");
        initView();
    }

    private void initView() {
        mCloseTv = (TextView)findViewById(R.id.admad_library_close_tv);
        mTitleTv = (TextView)findViewById(R.id.admad_library_title);
        mTitleRl = (RelativeLayout)findViewById(R.id.admad_library_rl_title);
        video_fullView = (FrameLayout)findViewById(R.id.admad_library_video_fullView);
        layout_WebView = (FrameLayout)findViewById(R.id.admad_library_layout_webView);
        webView = (WebView)findViewById(R.id.admad_library_webview_goods);
        titleText = (TextView)findViewById(R.id.admad_library_title);
        backLayout = (RelativeLayout)findViewById(R.id.admad_library_backlayout);
        rlTitle = (RelativeLayout)findViewById(R.id.admad_library_rl_title);
        pbProgress = (ProgressBar)findViewById(R.id.admad_library_pb_progress);
        backLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                AdDetailActivity.this.finish();
            }
        });

        try {
            mTitleRl.setBackgroundColor(Color.parseColor("#50000000"));
            int var1 = Color.parseColor("#ffffff");
            mCloseTv.setTextColor(var1);
            mTitleTv.setTextColor(var1);
        } catch (Exception var2) {
            TPLogger.e( var2.getMessage());
        }

        init();
    }

    private void init() {
//        detailHelper = new admsdk.library.b.a(getApplicationContext(), channel);
        WebSettings var1 = webView.getSettings();
        var1.setJavaScriptEnabled(true);
        var1.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setDownloadListener(new AdDetailActivity.MyDownloadListener());
        webView.addJavascriptInterface(new AdDetailActivity.JavaIml(), "local_obj");
        var1.setDomStorageEnabled(true);
        var1.setDefaultTextEncodingName("UTF-8");
        var1.setDatabaseEnabled(true);
        String var2 = mContext.getDir("database", 0).getPath();
        var1.setGeolocationEnabled(true);
        var1.setGeolocationDatabasePath(var2);
        var1.setSupportZoom(true);
        var1.setBuiltInZoomControls(true);
        var1.setLoadsImagesAutomatically(true);
        webView.clearHistory();
        webView.clearFormData();
        webView.clearCache(true);
        CookieManager var3 = CookieManager.getInstance();
        var3.setAcceptCookie(true);
        var3.removeSessionCookie();
        var3.removeAllCookie();
        if (Build.VERSION.SDK_INT >= 11) {
            webView.setLayerType(1, (Paint)null);
        }

        try {
            Method var4 = webView.getClass().getMethod("setLayerType", Integer.TYPE, Paint.class);
            if (var4 != null) {
                var4.invoke(webView, 1, null);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 7) {
            var1.setDomStorageEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 11) {
            webView.setLayerType(2, (Paint)null);
            webView.getSettings().setDisplayZoomControls(false);
        }

        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setSupportMultipleWindows(true);
        String var7 = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(var7);
        webView.setScrollBarStyle(View.ACCESSIBILITY_LIVE_REGION_NONE);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(2);
        }

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView var1, SslErrorHandler var2, SslError var3) {
                var2.proceed();
            }

            public WebResourceResponse shouldInterceptRequest(WebView var1, String var2) {
                TPLogger.i(var2);
                if (MatchUtil.isHttp(var2)) {
                    try {
                        Uri var3 = Uri.parse(var2);
                        Intent var4 = new Intent("android.intent.action.VIEW", var3);
                        var4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        AdDetailActivity.this.startActivity(var4);
                    } catch (Exception var5) {
                        return super.shouldInterceptRequest(var1, var2);
                    }
                }

                return super.shouldInterceptRequest(var1, var2);
            }

            public boolean shouldOverrideUrlLoading(WebView var1, String var2) {
               TPLogger.i("shouldOverrideUrlLoading" + var2);
                if (var2 == null) {
                    return false;
                } else if (MatchUtil.isHttp(var2)) {
                    try {
                        Uri var12 = Uri.parse(var2);
                        Intent var13 = new Intent("android.intent.action.VIEW", var12);
                        var13.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//268435456
                        AdDetailActivity.this.startActivity(var13);
                        return true;
                    } catch (Exception var9) {
                        return true;
                    }
                } else if (var2.startsWith("tel:")) {
                    Intent var11 = new Intent("android.intent.action.DIAL");
                    var11.setData(Uri.parse(var2));
                    AdDetailActivity.this.startActivity(var11);
                    return true;
                } else if (var2.contains(".apk")) {
                    TPLogger.i("shouldOverrideUrlLoading" + var2);
                    AdDetailActivity.this.startDownload(var2);
                    return true;
                } else {
                    String var3 = "";
                    if (var3 == null || var3.length() == 0) {
                        var3 = AdDetailActivity.this.keyWord;
                    }

                    String[] var4 = new String[0];
                    if (var3 != null && var3.length() > 0) {
                        var4 = var3.split(",");
                    }

                    for(int var5 = 0; var5 < var4.length; ++var5) {
                        String var6 = var4[var5] + "://";
                        if (var2.startsWith(var6)) {
                            if (AdDetailActivity.this.isAvilible(AdDetailActivity.this.mContext, "com.taobao.taobao") && var6.equals("taobao://") || AdDetailActivity.this.isAvilible(AdDetailActivity.this.mContext, "com.taobao.taobao") && var6.equals("intent://") || AdDetailActivity.this.isAvilible(AdDetailActivity.this.mContext, "com.tmall.wireless") && var6.equals("tmall://")) {
                                Uri var7 = Uri.parse(var2);

                                try {
                                    AdDetailActivity.this.startActivity(new Intent("android.intent.action.VIEW", var7));
                                } catch (Exception var10) {
                                    return true;
                                }
                            }

                            return true;
                        }
                    }

                    if (!var2.contains("http://") && !var2.contains("https://")) {
                        if (!var2.startsWith("http://") && !var2.startsWith("https://")) {
                            AdDetailActivity.this.jump2OtherApp(var2);
                        }

                        return true;
                    } else {
                        return false;
                    }
                }
            }

            public void onPageStarted(WebView var1, String var2, Bitmap var3) {
                super.onPageStarted(var1, var2, var3);
//                if (AdDetailActivity.this.detailHelper != null) {
//                    AdDetailActivity.this.detailHelper.a(var2);
//                }

            }

            public void onPageFinished(WebView var1, String var2) {
                if (var1 != null) {
                    var1.loadUrl("javascript:window.local_obj.showSource('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }

                super.onPageFinished(var1, var2);
//                if (AdDetailActivity.this.detailHelper != null) {
//                    AdDetailActivity.this.detailHelper.b(var2);
//                }

            }

            public void onFormResubmission(WebView var1, Message var2, Message var3) {
                super.onFormResubmission(var1, var2, var3);
                var3.sendToTarget();
            }

            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                super.onReceivedError(var1, var2, var3, var4);
                if (AdDetailActivity.this.webView.canGoBack()) {
                    AdDetailActivity.this.webView.goBack();
                } else if (AdDetailActivity.this.webView != null) {
                    AdDetailActivity.this.webView.setVisibility(View.GONE);
                }

            }
        });
        if (url.contains("bosslive")) {
            VideoWebChromeClient var5 = new VideoWebChromeClient(layout_WebView, video_fullView, this);
            var5.setOnToggledFullscreen(new VideoWebChromeClient.ToggledFullscreenCallback() {
                public void toggledFullscreen(boolean var1) {
                    if (var1) {
                        AdDetailActivity.this.rlTitle.setVisibility(View.GONE);
                        AdDetailActivity.this.getWindow().setFlags(1024, 1024);
                        AdDetailActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    } else {
                        AdDetailActivity.this.rlTitle.setVisibility(View.VISIBLE);
                        AdDetailActivity.this.getWindow().setFlags(2048, 1024);
                        AdDetailActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }

                }

                public void getTitle(String var1) {
                    if (var1 == null) {
                        var1 = "";
                    }

                    if (var1.length() > 8) {
                        var1 = var1.substring(0, 8);
                    }

                    AdDetailActivity.this.titleText.setText(var1);
                }
            });
            webView.setWebChromeClient(var5);
        } else {
            myWebChromeClient = new MyWebChromeClient();
            webView.setWebChromeClient(myWebChromeClient);
        }

        loadurl(webView, url);
    }

    private void startDownload(final String var1) {
//        if (admsdk.library.a.a.a) {
//            AlertDialog.Builder var2 = new AlertDialog.Builder(this);
//            var2.setTitle("提示");
//            var2.setMessage("确认去下载此应用吗？");
//            var2.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface var1x, int var2) {
//                    AdDetailActivity.this.startDownloadService(var1, AdDetailActivity.this.key, AdDetailActivity.this.type);
//                    var1x.dismiss();
//                }
//            });
//            var2.setNegativeButton("取消", (android.content.DialogInterface.OnClickListener)null);
//            downloadDialog = var2.create();
//            downloadDialog.show();
//        } else {
            startDownloadService(var1, key, type);
//        }

    }
    DownloadManager dm;
    private void startDownloadService(String url, String key, String type) {
        Toast.makeText(getApplicationContext(), "开始下载", Toast.LENGTH_SHORT).show();
//        Intent var4 = new Intent(this, DownloadService.class);
//        var4.putExtra("url", var1);
//        var4.putExtra("key", var2);
//        var4.putExtra("type", var3);
//        startService(var4);
        dm = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(url));
        request.setMimeType("application/vnd.android.package-archive");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalFilesDir(this,
                Environment.DIRECTORY_DOWNLOADS, "下载");
        //添加下面这段代码　　　　//添加下面这段代码
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        dm.enqueue(request);
    }

    private void jump2OtherApp(String var1) {
        try {
            Uri var2 = Uri.parse(var1);
            Intent var3 = new Intent();
            var3.setAction("android.intent.action.VIEW");
            var3.setData(var2);
            var3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(var3);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private Intent createCameraIntent() {
        new Intent("android.media.action.IMAGE_CAPTURE");
        Intent var2 = new Intent("android.intent.action.GET_CONTENT");
        var2.setType("image/*");
        return var2;
    }

    @TargetApi(16)
    public void onActivityResult(int var1, int var2, Intent var3) {
        if (var1 == 12343 && var2 == RESULT_OK) {
            Uri var4 = null == var3 ? null : var3.getData();
            if (mFilePathCallback != null) {
                Uri[] var5 = null;
                if (var3 != null) {
                    String var6 = var3.getDataString();
                    ClipData var7 = var3.getClipData();
                    if (var7 != null) {
                        var5 = new Uri[var7.getItemCount()];

                        for(int var8 = 0; var8 < var7.getItemCount(); ++var8) {
                            ClipData.Item var9 = var7.getItemAt(var8);
                            var5[var8] = var9.getUri();
                        }
                    }

                    if (var6 != null) {
                        var5 = new Uri[]{Uri.parse(var6)};
                    }
                }

                mFilePathCallback.onReceiveValue(var5);
                mFilePathCallback = null;
            } else if (mUploadFile != null) {
                mUploadFile.onReceiveValue(var4);
                mUploadFile = null;
            }
        }

        super.onActivityResult(var1, var2, var3);
    }

    private boolean isAvilible(Context var1, String var2) {
        PackageManager var3 = var1.getPackageManager();
        List var4 = var3.getInstalledPackages(0);
        ArrayList var5 = new ArrayList();
        if (var4 != null) {
            for(int var6 = 0; var6 < var4.size(); ++var6) {
                String var7 = ((PackageInfo)var4.get(var6)).packageName;
                var5.add(var7);
            }
        }

        return var5.contains(var2);
    }

    private void loadurl(WebView var1, String var2) {
        HashMap var3 = new HashMap();
        var3.put("Referer", tempreferer);
        var1.loadUrl(var2, var3);
        tempreferer = var2;
    }

    public void onResume() {
        super.onResume();

        try {
            webView.getClass().getMethod("onResume").invoke(webView, (Object[])null);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void onPause() {
        super.onPause();

        try {
            webView.getClass().getMethod("onPause").invoke(webView, (Object[])null);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean dispatchKeyEvent(KeyEvent var1) {
        if (var1.getKeyCode() == 4 && var1.getAction() == 0) {
            finish();
        }

        return false;
    }

    protected void onDestroy() {
//        if (detailHelper != null) {
//            detailHelper.a();
//        }

        ViewGroup var1 = (ViewGroup)getWindow().getDecorView();
        var1.removeAllViews();
        video_fullView.removeAllViews();
        if (webView != null) {
            ViewParent var2 = webView.getParent();
            if (var2 != null && var2 instanceof ViewGroup) {
                ((ViewGroup)var2).removeAllViews();
            }

            webView.setVisibility(View.GONE);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }

        if (downloadDialog != null) {
            downloadDialog.dismiss();
            downloadDialog = null;
        }

        if (permisionDialog != null) {
            permisionDialog.dismiss();
            permisionDialog = null;
        }

        super.onDestroy();
    }

    private class MyDownloadListener implements DownloadListener {
        private MyDownloadListener() {
        }

        public void onDownloadStart(String var1, String var2, String var3, String var4, long var5) {
            AdDetailActivity.this.startDownload(var1);
            TPLogger.i("去下载" + var1);
        }
    }

    final class JavaIml {
        JavaIml() {
        }

        @JavascriptInterface
        public void showSource(String var1) {
            Pattern var2 = Pattern.compile("^[\\s\\S]*?<body[\\s\\S]*?>[\\s\\S]*?<img.*?src=\\\"((http\\:|//|https\\:).*?)\\\"", Pattern.CASE_INSENSITIVE);
            Matcher var3 = var2.matcher(var1);
            String var4 = "";
            if (var3.find()) {
                var4 = var3.group(1);
                if (var4.endsWith("\"")) {
                    var4 = var4.substring(0, var4.length() - 1);
                }

                if (var4.startsWith("//")) {
                    var4 = "http:" + var4;
                }

                if (!var4.startsWith("http://") && !var4.startsWith("https://")) {
                    var4 = "";
                }
            }

        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void onGeolocationPermissionsShowPrompt(String var1, GeolocationPermissions.Callback var2) {
            var2.invoke(var1, true, false);
            super.onGeolocationPermissionsShowPrompt(var1, var2);
        }

        public void onReceivedTitle(WebView var1, String var2) {
            super.onReceivedTitle(var1, var2);
            if (var2 == null) {
                var2 = "";
            }

            if (var2.length() > 8) {
                var2 = var2.substring(0, 8);
            }

            AdDetailActivity.this.titleText.setText(var2);
        }

        public boolean onShowFileChooser(WebView var1, ValueCallback<Uri[]> var2, FileChooserParams var3) {
            AdDetailActivity.this.mFilePathCallback = var2;
            Intent var4 = new Intent("android.intent.action.GET_CONTENT");
            var4.addCategory("android.intent.category.OPENABLE");
            var4.setType("image/*");
            AdDetailActivity.this.startActivityForResult(Intent.createChooser(var4, "File Browser"), 12343);
            return true;
        }

        public void onProgressChanged(WebView var1, int var2) {
            if (var2 == 100) {
                AdDetailActivity.this.pbProgress.setVisibility(View.GONE);
            } else {
                AdDetailActivity.this.pbProgress.setProgress(var2);
            }

            super.onProgressChanged(var1, var2);
        }
    }
}

