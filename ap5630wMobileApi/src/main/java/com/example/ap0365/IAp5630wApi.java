package com.example.ap0365;

import crop.computer.askey.androidlib.http.request.RequestCallback;

public interface IAp5630wApi {
    void login(String username, String password, RequestCallback callback);
    void setAccountPassword(String password, RequestCallback callback);
    void getOnboardStatus(RequestCallback callback);
    void setOnboardStatus(String action, RequestCallback callback);
    void getDeviceCount(RequestCallback callback);
    void logout();
}
