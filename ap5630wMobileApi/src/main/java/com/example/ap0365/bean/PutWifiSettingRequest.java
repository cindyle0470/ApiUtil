package com.example.ap0365.bean;

public class PutWifiSettingRequest {
    public String ssid;
    public String pwd;
    public int encryption;

    public PutWifiSettingRequest(String ssid, String pwd, int encryption) {
        this.ssid = ssid;
        this.pwd = pwd;
        this.encryption = encryption;
    }
}
