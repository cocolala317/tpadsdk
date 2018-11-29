package com.tianpeng.tpad_sdk.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuHong on 2018/11/14 0014.
 */
public class ADResponseBean {


    /**
     * ads : [{"action":2,"adh":100,"adlogo":"http://stxxxo/zhxxyun.png","adw":640,"click_report":["http://xxx"],"image_url":["http://xxx2745.jpg"],"inventory_type":1,"landing_url":"http://tssss_=","show_report":["http:sssEdQEB&cb=1","http://sss"]}]
     * code : 0
     */

    private int code;
    private String msg;
    private List<AdsBean> ads;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public static class AdsBean {
        /**
         * action : 2
         * adh : 100
         * adlogo : http://stxxxo/zhxxyun.png
         * adw : 640
         * click_report : ["http://xxx"]
         * image_url : ["http://xxx2745.jpg"]
         * inventory_type : 1
         * landing_url : http://tssss_=
         * show_report : ["http:sssEdQEB&cb=1","http://sss"]
         */

        private int action_type;
        private int inventory_type;
        private int action;
        private int adh;
        private String adlogo;
        private String source;
        private String html;
        private int adw;
        private int file_size;
        private String landing_url;
        private String deeplink_url;
        private String req_download_url;
        private String package_name;
        private String icon;
        private String title;
        private String desc;
        private String download_file_name;
        private List<String> click_report;
        private List<String> image_url;
        private List<String> show_report;
        private List<String> deeplink_report;
        private List<String> load_report;
        private List<String> app_downstart_report;
        private List<String> app_downend_report;
        private List<String> app_installstart_report;
        private List<String> app_open_report;
        private List<String> app_installend_report;
        private VideoBean video;

        public int getAction_type() {
            return action_type;
        }

        public void setAction_type(int action_type) {
            this.action_type = action_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public int getFile_size() {
            return file_size;
        }

        public void setFile_size(int file_size) {
            this.file_size = file_size;
        }

        public String getDeeplink_url() {
            return deeplink_url;
        }

        public void setDeeplink_url(String deeplink_url) {
            this.deeplink_url = deeplink_url;
        }

        public String getReq_download_url() {
            return req_download_url;
        }

        public void setReq_download_url(String req_download_url) {
            this.req_download_url = req_download_url;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDownload_file_name() {
            return download_file_name;
        }

        public void setDownload_file_name(String download_file_name) {
            this.download_file_name = download_file_name;
        }

        public List<String> getDeeplink_report() {
            return deeplink_report;
        }

        public void setDeeplink_report(List<String> deeplink_report) {
            this.deeplink_report = deeplink_report;
        }

        public List<String> getLoad_report() {
            return load_report;
        }

        public void setLoad_report(List<String> load_report) {
            this.load_report = load_report;
        }

        public List<String> getApp_downstart_report() {
            return app_downstart_report;
        }

        public void setApp_downstart_report(List<String> app_downstart_report) {
            this.app_downstart_report = app_downstart_report;
        }

        public List<String> getApp_downend_report() {
            return app_downend_report;
        }

        public void setApp_downend_report(List<String> app_downend_report) {
            this.app_downend_report = app_downend_report;
        }

        public List<String> getApp_installstart_report() {
            return app_installstart_report;
        }

        public void setApp_installstart_report(List<String> app_installstart_report) {
            this.app_installstart_report = app_installstart_report;
        }

        public List<String> getApp_open_report() {
            return app_open_report;
        }

        public void setApp_open_report(List<String> app_open_report) {
            this.app_open_report = app_open_report;
        }

        public List<String> getApp_installend_report() {
            return app_installend_report;
        }

        public void setApp_installend_report(List<String> app_installend_report) {
            this.app_installend_report = app_installend_report;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public int getAdh() {
            return adh;
        }

        public void setAdh(int adh) {
            this.adh = adh;
        }

        public String getAdlogo() {
            return adlogo;
        }

        public void setAdlogo(String adlogo) {
            this.adlogo = adlogo;
        }

        public int getAdw() {
            return adw;
        }

        public void setAdw(int adw) {
            this.adw = adw;
        }

        public int getInventory_type() {
            return inventory_type;
        }

        public void setInventory_type(int inventory_type) {
            this.inventory_type = inventory_type;
        }

        public String getLanding_url() {
            return landing_url;
        }

        public void setLanding_url(String landing_url) {
            this.landing_url = landing_url;
        }

        public List<String> getClick_report() {
            return click_report;
        }

        public void setClick_report(List<String> click_report) {
            this.click_report = click_report;
        }

        public List<String> getImage_url() {
            return image_url;
        }

        public void setImage_url(List<String> image_url) {
            this.image_url = image_url;
        }

        public List<String> getShow_report() {
            return show_report;
        }

        public void setShow_report(List<String> show_report) {
            this.show_report = show_report;
        }

        public class VideoBean{
            private String start;
            private String end;
            private String url;
            private int play_duration;
            private ArrayList<String> player_start_trackers;
            private ArrayList<String> player_end_trackers;
            private ArrayList<String> target_page_show_trackers;
            private ArrayList<String> target_page_click_trackers;

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getPlay_duration() {
                return play_duration;
            }

            public void setPlay_duration(int play_duration) {
                this.play_duration = play_duration;
            }

            public ArrayList<String> getPlayer_start_trackers() {
                return player_start_trackers;
            }

            public void setPlayer_start_trackers(ArrayList<String> player_start_trackers) {
                this.player_start_trackers = player_start_trackers;
            }

            public ArrayList<String> getPlayer_end_trackers() {
                return player_end_trackers;
            }

            public void setPlayer_end_trackers(ArrayList<String> player_end_trackers) {
                this.player_end_trackers = player_end_trackers;
            }

            public ArrayList<String> getTarget_page_show_trackers() {
                return target_page_show_trackers;
            }

            public void setTarget_page_show_trackers(ArrayList<String> target_page_show_trackers) {
                this.target_page_show_trackers = target_page_show_trackers;
            }

            public ArrayList<String> getTarget_page_click_trackers() {
                return target_page_click_trackers;
            }

            public void setTarget_page_click_trackers(ArrayList<String> target_page_click_trackers) {
                this.target_page_click_trackers = target_page_click_trackers;
            }
        }
    }

}
