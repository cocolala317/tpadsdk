package com.tianpeng.tpad_sdk.sdk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tianpeng.tpad_sdk.data.GetADParam;
import com.tianpeng.tpad_sdk.utils.NetUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by YuHong on 2018/11/14 0014.
 */
public class TPengADGenSDK {
    private Context context;
    private String appKey,splashPid,bannerPid,infoPid;
    private String ver;
    private GetADParam param;
    private static volatile TPengADGenSDK instance = null;
    public static TPengADGenSDK getInstance(){
        if (instance == null) {
            synchronized (TPengADGenSDK.class) {
                if (instance == null) {
                    instance = new TPengADGenSDK();
                }
            }
        }
        return instance;
    }
    public TPengADGenSDK initSDK(Context context, String appKey,String version){
        if (context == null) {
            throw new RuntimeException("the context is null !!");
        } else if (TextUtils.isEmpty(appKey)) {
            throw new RuntimeException("appKey must not be empty !!");
        } else if (TextUtils.isEmpty(version)) {
            throw new RuntimeException("version must not be empty !!");
        }else {
            this.ver = version;
            this.context = context.getApplicationContext();
            this.appKey = appKey;
            setRequestParam();
        }
        return this;
    }
    public Context getAdSdkContext() {
        if (context == null) {
            throw new RuntimeException("the context is null, please init sdk first !!");
        } else {
            return context;
        }
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSdkName() {
        return "com.tianpeng.tpad_sdk";
    }
    public TPengADGenSDK setSplashPid(String pid) {
        this.splashPid = pid;
        return this;
    }
    public String getSplashPid() {
        return splashPid;
    }
    public TPengADGenSDK setBannerPid(String pid) {
        this.bannerPid = pid;
        return this;
    }
    public String getBannerPid() {
        return bannerPid;
    }
    public TPengADGenSDK setInfoPid(String pid) {
        this.infoPid = pid;
        return this;
    }
    public String getInfoPid() {
        return infoPid;
    }

    private void setRequestParam(){
        GetADParam.AppBean appBean = new GetADParam.AppBean();
        appBean.setBundle(context.getPackageName());
        appBean.setName(context.getApplicationInfo().loadLabel(context.getPackageManager()).toString());
        GetADParam.DeviceBean deviceBean = new GetADParam.DeviceBean();
        deviceBean.setIp(getLocalIpAddress());
        deviceBean.setAnid(ID(context));
        deviceBean.setImei(getDeviceId(context).get(0));
        deviceBean.setImsi(getIMSI(context));
        deviceBean.setMac(MAC(context));
        deviceBean.setOs(1);
        deviceBean.setOsv(Build.VERSION.RELEASE);
        deviceBean.setBrand(Build.BRAND);
        deviceBean.setModel(Build.MODEL);
        deviceBean.setUa(UA(context));//浏览器 userAgent
        deviceBean.setType(isPad(context)?1:0);//设备类型(0 – phone， 1 – pad， 2 – 其他)
        deviceBean.setSw((int) (getAndroiodScreenProperty(context).widthPixels / getAndroiodScreenProperty(context).density));//宽度
        deviceBean.setSh((int) (getAndroiodScreenProperty(context).heightPixels / getAndroiodScreenProperty(context).density));//高度
        deviceBean.setLang("zh-cn");
        deviceBean.setMcc(MCC(context));//移动国家码，中国： 460
        deviceBean.setMnc(MNC(context));//移动网络码，移动 00/02/07，联通 01/06，电信 03/05/11
        deviceBean.setLat(getLocation(context)!=null&&getLocation(context).size()>0?getLocation(context).get(0):0);//经度
        deviceBean.setLon(getLocation(context)!=null&&getLocation(context).size()>1?getLocation(context).get(1):0);//维度
        deviceBean.setOrientation(String.valueOf(ORIENTATION(context)));//横竖屏0–竖屏， 1–横屏
        deviceBean.setConnection(NetUtils.getNetworkState(context));//网络类型： 0-未知， 1-wifi， 2-2G， 3-3G， 4-4G
        param = new GetADParam();
        param.setAppkey(appKey);
        param.setVer(ver);
//        param.setPid(pid);
        param.setApp(appBean);
        param.setDevice(deviceBean);

    }

    public GetADParam getRequestParam(){
        return this.param;
    }

    /**
     * 得到设备mac地址
     */
    private static String MAC(Context mContext) {
        String mac = "NO Search";
        WifiManager manager = (WifiManager) mContext
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        mac = info.getMacAddress();
        return mac;
    }
    /**
     * 得到设备mac地址
     */
    private static List<Double> getLocation(Context context) {
        LocationManager m_location_manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        List<Double> lats = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lm = m_location_manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(lm!=null) {
                lats.add(lm.getLatitude());
                lats.add(lm.getLongitude());
            }
        }
        return lats;
    }
    private static int ORIENTATION(Context mContext) {
        Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
           return 1;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            return 0;
        }
        return 0;
    }
    private static DisplayMetrics getAndroiodScreenProperty(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

        Log.d("h_bl", "屏幕宽度（像素）：" + width);
        Log.d("h_bl", "屏幕高度（像素）：" + height);
        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
        return dm;
    }
    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    private static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    private static String UA(Context mContext) {
        WebView webview;
        webview = new WebView(mContext);
        webview.layout(0, 0, 0, 0);
        WebSettings settings = webview.getSettings();
        String ua = settings.getUserAgentString();
        return ua;
    }

    private static  String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
//            Log.e(LOG_TAG, ex.toString());
        }
        return "";
    }
    private static List<String> getDeviceId(final Context context) {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        List<String> strings = new ArrayList<>();
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    for(int i=0;i<tm.getPhoneCount();i++){
                        strings.add( tm.getDeviceId(i));
                    }
                }else {
                    strings.add(tm.getDeviceId());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(strings.size()==0) {
            strings.add("0");
            strings.add("0");
        }else if(strings.size()==1){
            strings.add("0");
        }
        return strings;
    }
    /**
     * 获取手机IMSI
     */
    private static String getIMSI(Context context){
        String imsi="";
        try {
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            //获取IMSI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                imsi = telephonyManager.getSubscriberId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imsi;
    }
    private static String MCC(Context context){
        String MCC="";
        try {
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            //获取IMSI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                MCC = telephonyManager.getSimOperator();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MCC;
    }
    private static String MNC(Context context){
        String MNC="";
        try {
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            //获取IMSI号
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                MNC = telephonyManager.getNetworkOperator();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MNC;
    }
    /**
     * 返回安卓设备ID
     */
    private static String ID(Context context) {
        String id = "NO Search";
        id = android.provider.Settings.Secure.getString(
                context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        return id;
    }

}
