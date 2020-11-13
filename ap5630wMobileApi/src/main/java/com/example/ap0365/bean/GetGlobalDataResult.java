package com.example.ap0365.bean;

import java.util.List;

public class GetGlobalDataResult extends BasicResult{

    public String firmware_version;
    public String current_client;
    public int device_num_per_page;
    public int router_mode;
    public int mix_mode;
    public String device_type;
    public int mesh_mode;
    public List<NameValuePair> mesh_mode_list;
    public List<NameValuePair> router_mode_list;
    public List<NameValuePair> mix_mode_list;
    public List<Link> about;
    public List<WaitTime> wait_time;
    public TimeOut time_out;
    public List<String> disconnect_api;
    public List<Feature> support_feature;
    public Wifi wifi;

    public GetGlobalDataResult(String status, String firmware_version, String current_client, int device_num_per_page, int router_mode, int mix_mode, String device_type, int mesh_mode, List<NameValuePair> mesh_mode_list, List<NameValuePair> router_mode_list, List<NameValuePair> mix_mode_list, List<Link> about, List<WaitTime> wait_time, TimeOut time_out, List<String> disconnect_api, List<Feature> support_feature, Wifi wifi) {
        super(status);
        this.firmware_version = firmware_version;
        this.current_client = current_client;
        this.device_num_per_page = device_num_per_page;
        this.router_mode = router_mode;
        this.mix_mode = mix_mode;
        this.device_type = device_type;
        this.mesh_mode = mesh_mode;
        this.mesh_mode_list = mesh_mode_list;
        this.router_mode_list = router_mode_list;
        this.mix_mode_list = mix_mode_list;
        this.about = about;
        this.wait_time = wait_time;
        this.time_out = time_out;
        this.disconnect_api = disconnect_api;
        this.support_feature = support_feature;
        this.wifi = wifi;
    }

    public static class Link {
        public String title;
        public String url;

        public Link(String title, String url) {
            this.title = title;
            this.url = url;
        }
    }

    public static class WaitTime {
        public String API;
        public int wait_time;

        public WaitTime(String API, int wait_time) {
            this.API = API;
            this.wait_time = wait_time;
        }
    }

    public static class TimeOut {
        public int system_using_time;
        public int API_wait_time;

        public TimeOut(int system_using_time, int API_wait_time) {
            this.system_using_time = system_using_time;
            this.API_wait_time = API_wait_time;
        }
    }

    public static class Feature {

        public String title;
        public List<String> sub_title;

        public Feature(String title, List<String> sub_title) {
            this.title = title;
            this.sub_title = sub_title;
        }
    }

    public static class Wifi {
        public GuestWifi guest_wifi;
        public List<Radio> radio_list;
        public List<NameValuePair> security_list;
        public List<Channel> channel_list;
        public Channel.Setting channel_setting;
        public List<Ssid> ssids;
        public List<BandwidthOption> bandwidth_options;

        public Wifi(GuestWifi guest_wifi, List<Radio> radio_list, List<NameValuePair> security_list, List<Channel> channel_list, Channel.Setting channel_setting, List<Ssid> ssids, List<BandwidthOption> bandwidth_options) {
            this.guest_wifi = guest_wifi;
            this.radio_list = radio_list;
            this.security_list = security_list;
            this.channel_list = channel_list;
            this.channel_setting = channel_setting;
            this.ssids = ssids;
            this.bandwidth_options = bandwidth_options;
        }

        public static class GuestWifi {
            public String support;
            public int support_number;

            public GuestWifi(String support, int support_number) {
                this.support = support;
                this.support_number = support_number;
            }
        }

        public static class Radio {
            public String name;
            public int inter_value;
            public String support;

            public Radio(String name, int inter_value, String support) {
                this.name = name;
                this.inter_value = inter_value;
                this.support = support;
            }
        }

        public static class Channel {
            public int inter_value;
            public List<Integer> list;

            public Channel(int inter_value, List<Integer> list) {
                this.inter_value = inter_value;
                this.list = list;
            }

            public static class Setting {
                public String auto_mode;
                public String manual_mode;

                public Setting(String auto_mode, String manual_mode) {
                    this.auto_mode = auto_mode;
                    this.manual_mode = manual_mode;
                }
            }
        }

        public static class Ssid {
            /**
             * "inter_value":0,int(0,1,2...)
             *                 "list":
             *                 [
             *                     {
             *                         "index":str,
             *                         "name":str,
             *                         "support":str{"true","false"}
             *                     }
             *                 ],(dynamic array, less than 8)
             */
            public int inter_value;
            public List<SsidInfo> list;

            public static class SsidInfo {
                public String index;
                public String name;
                public String support;

                public SsidInfo(String index, String name, String support) {
                    this.index = index;
                    this.name = name;
                    this.support = support;
                }
            }

        }
        public static class BandwidthOption {
            public int inter_value;

            public static class BandwidthOptionInfo {
                public String name;
                public int value;
                public List<Integer> show_channel;

                public BandwidthOptionInfo(String name, int value, List<Integer> show_channel) {
                    this.name = name;
                    this.value = value;
                    this.show_channel = show_channel;
                }
            }
        }

    }


    /**
     *  "status": "success",
     *     "firmware_version": "1.0.8",(str)
     *     "current_client":str,(MAC)(Current mac that system get from client)
     *     "device_num_per_page":int,
     *     "router_mode":int{0,1,2},(phase 2 not use)
     *     "mix_mode":int(server part, mesh + router has no detail design currently, so does device mode in future)
     *     "device_type":(str){"Mesh","Router","Mix"}, (suppose has pure router device. Mix means mesh + router)
     *     "mesh_mode":int,
     *     "mesh_mode_list":[ (here 2 list will send by server, list content and mapping value possibly will change in future)
     *         {
     *             "name":"RE",
     *             "value":1
     *         },
     *         {
     *             "name":"CAP",
     *             "value":2
     *         }
     *     ],
     *     "router_mode_list":[
     *         {
     *             "name":"Router",
     *             "value":0
     *         }
     *     ],
     *     "mix_mode_list":[
     *         {
     *             "name":"Router",
     *             "value":0
     *         }
     *     ],
     *     "about":
     *     [
     *         {
     *             "title":str,
     *             "url":str
     *         }
     *     ],
     *     "wait_time":
     *     [
     *         {
     *             "API":API index (str),
     *             "wait_time":int
     *         }
     *     ],
     *     "time_out":(0 means no timeout)
     *     {
     *         "system_using_time":int,
     *         "API_wait_time":int
     *     },
     *     "disconnect_api":(api which will cause wifi connection interrupt)
     *     [
     *         (API index)
     *     ],
     *     "support_feature":[
     *         {
     *             "title":,{"wan","lan","wifi","mesh","parental_control","clients"}
     *             "sub_title":[](dynamic array)
     *         }
     *         {(example)
     *             "title":"wifi",
     *             "sub_title":
     *             [
     *                 "network",
     *                 "advance"
     *             ]
     *         }
     *     ], (dynamic array)
     *     "wifi":
     *     {
     *         "guest_wifi":
     *         {
     *             "support":str{"true","false"},
     *             "support_number":int(>0)
     *         },
     *         "radio_list": (when length == 0, show 1 wifi setting without radios)
     *         [
     *             {
     *                 "name": "2.4G",(2.4G,5G,5G2...)
     *                 "inter_value": 0,int(0,1,2...)
     *                 "support":str{"not_support","read_only","read_write"}
     *             }
     *         ],
     *         "security_list": (when length == 0, remove encryption setting)
     *         [
     *             {
     *                 "name": "WPA2 AES",
     *                 "value":int
     *             },
     *             {
     *                 "name": "WPA MIX TKIP + AES",
     *                 "value":int
     *             }
     *         ],
     *         "channel_list":
     *         [
     *             {
     *                 "inter_value":0,int(0,1,2...),
     *                 "list":[](dynamic array)
     *             }
     *         ],
     *         "channel_setting":
     *         {
     *             "auto_mode":str{"disable_setting","hide_setting","enable_setting"},
     *             "manual_mode":str{"disable_setting","hide_setting","enable_setting"}
     *         }
     *         "ssids":
     *         [
     *             {
     *                 "inter_value":0,int(0,1,2...)
     *                 "list":
     *                 [
     *                     {
     *                         "index":str,
     *                         "name":str,
     *                         "support":str{"true","false"}
     *                     }
     *                 ],(dynamic array, less than 8)
     *             }
     *         ],
     *         "bandwidth_options":
     *         [
     *             {
     *                 "inter_value":0,int(0,1,2...)
     *                 "list":
     *                 [
     *                     {
     *                         "name":str,
     *                         "value":int,
     *                         "show_channel": [](dynamic array)
     *                     },
     *                     {
     *                         "name":str,
     *                         "value":int,
     *                         "show_channel": [100,112...](dynamic array)
     *                     }
     *                 ](dynamic array),
     *             }
     *         ]
     *     }
     */
}
