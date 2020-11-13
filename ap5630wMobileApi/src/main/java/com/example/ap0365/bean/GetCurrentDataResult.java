package com.example.ap0365.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCurrentDataResult extends BasicResult {

    public Wifi wifi;
    public System system;
    public String network_status;

    public GetCurrentDataResult(String status, Wifi wifi, System system, String network_status) {
        super(status);
        this.wifi = wifi;
        this.system = system;
        this.network_status = network_status;
    }
    public static class Wifi {

        @SerializedName("interface")
        public List<Interface> _interface;

        public Wifi(List<Interface> _interface) {
            this._interface = _interface;
        }

        public static class Interface {

            /**
             * "inter_value": int(0,1,2...), (get from global data)
             *                 "bandwidth": int,
             *                 "channel": int, (auto = -1)
             *                 "current_channel":int,
             *                 "ssid":
             *                 [
             *                     {
             *                         "index":int, (not 0 means guest wifi)
             *                         "value": str, (interface value)
             *                         "name": str, (ssid text)
             *                         "security": int,
             *                         "password": str
             *                     }
             *                 ]
             */
            public int inter_value;
            public int bandwidth;
            public int channel;
            public int current_channel;
            public List<Ssid> ssid;

            public static class Ssid {
                public int index;
                public String value;
                public String name;
                public int security;
                public String password;

                public Ssid(int index, String value, String name, int security, String password) {
                    this.index = index;
                    this.value = value;
                    this.name = name;
                    this.security = security;
                    this.password = password;
                }
            }
        }
    }

    public static class System {
        /**
         * "lan_ip":str,
         *         "wan_ip":str,
         *         "mac":str,
         *         "dns":str,
         *         "alias":str,
         *         "account":str,
         *         "password":str,
         *         "current_mode":,{"RE","CAP","Router"}
         */

        public String lan_ip;
        public String wan_ip;
        public String mac;
        public String dns;
        public String alias;
        public String account;
        public String password;
        public String current_mode;

        public System(String lan_ip, String wan_ip, String mac, String dns, String alias, String account, String password, String current_mode) {
            this.lan_ip = lan_ip;
            this.wan_ip = wan_ip;
            this.mac = mac;
            this.dns = dns;
            this.alias = alias;
            this.account = account;
            this.password = password;
            this.current_mode = current_mode;
        }
    }
}
