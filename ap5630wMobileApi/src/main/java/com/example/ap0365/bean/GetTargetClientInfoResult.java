package com.example.ap0365.bean;

public class GetTargetClientInfoResult extends BasicResult{

    public TargetClientInfo target_client_info;

    public GetTargetClientInfoResult(String status, TargetClientInfo target_client_info) {
        super(status);
        this.target_client_info = target_client_info;
    }

    public static class TargetClientInfo {
        public String name;
        public String rx; // {ex: 20.3 Kbps}
        public String tx; // {ex: 20.3 Kbps}
        public String connect_type;
        public tw.com.askey.ap5630wmobileapi.bean.ConnectedPoint connect_to;
        public String ip;
        public String mac;

        public TargetClientInfo(String name, String rx, String tx, String connect_type, tw.com.askey.ap5630wmobileapi.bean.ConnectedPoint connect_to, String ip, String mac) {
            this.name = name;
            this.rx = rx;
            this.tx = tx;
            this.connect_type = connect_type;
            this.connect_to = connect_to;
            this.ip = ip;
            this.mac = mac;
        }
    }
}
