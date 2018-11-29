package com.tianpeng.tpad_sdk.data;

import java.util.List;

/**
 * Created by YuHong on 2018/11/14 0014.
 */
public class GetADParam {


    /**
     * ver : 2.0
     * appkey : bg76gil7
     * pid : an6o1ngv
     * need_https : 0
     * app : {"name":"DrivingTest","bundle":"org.linable.candy.snow2018"}
     * device : {"type":0,"osv":"6.0","brand":"Xiaomi","model":"Redmi Note 4","mac":"b0:e2:35:fd:76:ba","ua":"Mozilla","sw":1080,"sh":1920,"orientation":"1","density":3,"mcc":"460","mnc":"01","connection":1,"lang":"zh","os":1,"imei":"863100038994079","imsi":"460011062102506","anid":"ac4f7e622e5275a5"}
     * ads : [{"type":0,"place_id":"an6o1ngv","floor_price":0,"w":640,"h":100,"inventory_types":[1,2,4,5]}]
     */

    private String ver;
    private String appkey;
    private String pid;
    private int need_https;
    private AppBean app;
    private DeviceBean device;
    private List<AdsBean> ads;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getNeed_https() {
        return need_https;
    }

    public void setNeed_https(int need_https) {
        this.need_https = need_https;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public static class AppBean {
        /**
         * name : DrivingTest
         * bundle : org.linable.candy.snow2018
         */

        private String name;
        private String bundle;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBundle() {
            return bundle;
        }

        public void setBundle(String bundle) {
            this.bundle = bundle;
        }
    }

    public static class DeviceBean {
        /**
         * type : 0
         * osv : 6.0
         * brand : Xiaomi
         * model : Redmi Note 4
         * mac : b0:e2:35:fd:76:ba
         * ua : Mozilla
         * sw : 1080
         * sh : 1920
         * orientation : 1
         * density : 3
         * mcc : 460
         * mnc : 01
         * connection : 1
         * lang : zh
         * os : 1
         * imei : 863100038994079
         * imsi : 460011062102506
         * anid : ac4f7e622e5275a5
         */

        private int type;
        private String osv;
        private String ip;
        private Double lat;
        private Double lon;
        private String brand;
        private String model;
        private String mac;
        private String ua;
        private int sw;
        private int sh;
        private String orientation;
        private int density;
        private String mcc;
        private String mnc;
        private int connection;
        private String lang;
        private int os;
        private String imei;
        private String imsi;
        private String anid;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOsv() {
            return osv;
        }

        public void setOsv(String osv) {
            this.osv = osv;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getUa() {
            return ua;
        }

        public void setUa(String ua) {
            this.ua = ua;
        }

        public int getSw() {
            return sw;
        }

        public void setSw(int sw) {
            this.sw = sw;
        }

        public int getSh() {
            return sh;
        }

        public void setSh(int sh) {
            this.sh = sh;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public int getDensity() {
            return density;
        }

        public void setDensity(int density) {
            this.density = density;
        }

        public String getMcc() {
            return mcc;
        }

        public void setMcc(String mcc) {
            this.mcc = mcc;
        }

        public String getMnc() {
            return mnc;
        }

        public void setMnc(String mnc) {
            this.mnc = mnc;
        }

        public int getConnection() {
            return connection;
        }

        public void setConnection(int connection) {
            this.connection = connection;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public int getOs() {
            return os;
        }

        public void setOs(int os) {
            this.os = os;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getAnid() {
            return anid;
        }

        public void setAnid(String anid) {
            this.anid = anid;
        }
    }

    public static class AdsBean {
        /**
         * type : 0
         * place_id : an6o1ngv
         * floor_price : 0
         * w : 640
         * h : 100
         * inventory_types : [1,2,4,5]
         */

        private int type;
        private String place_id;
        private int floor_price;
        private int w;
        private int h;
        private List<Integer> inventory_types;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public int getFloor_price() {
            return floor_price;
        }

        public void setFloor_price(int floor_price) {
            this.floor_price = floor_price;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public List<Integer> getInventory_types() {
            return inventory_types;
        }

        public void setInventory_types(List<Integer> inventory_types) {
            this.inventory_types = inventory_types;
        }
    }
}
