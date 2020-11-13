package com.example.ap0365;

import android.content.Context;

import com.example.ap0365.bean.LoginRequest;
import com.example.ap0365.bean.PutAccountPasswordRequest;
import com.example.ap0365.bean.PutOnboardStatusRequest;

import java.util.ArrayList;
import java.util.List;

import crop.computer.askey.androidlib.http.remoteservice.BaseRemoteService;
import crop.computer.askey.androidlib.http.request.ExecutorRequestManager;
import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.XmlV2URLConfigManager;
import crop.computer.askey.androidlib.util.JSONParserUtil;

public class Ap5630wApi extends BaseRemoteService implements IAp5630wApi{
    private Context context;
    private String cacheSessionToken;

    public Ap5630wApi(Context context) {
        this.context = context;
    }

    @Override
    public void login(String username, String password, RequestCallback callback) {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        LoginRequest request = new LoginRequest(username, password);

        invoke("GET-LOGIN", rqProperties, null, null);
    }



    @Override
    public void setAccountPassword(String password, RequestCallback callback) {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        PutAccountPasswordRequest request = new PutAccountPasswordRequest(password);

        invoke("PUT-ACCOUNT_PWD", rqProperties, null, JSONParserUtil.toJson(request));
    }

    @Override
    public void getOnboardStatus(RequestCallback callback) {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        invoke("GET-ONBOARD_STATUS", rqProperties, null, null);
    }

    @Override
    public void setOnboardStatus(String action, RequestCallback callback) {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        PutOnboardStatusRequest request = new PutOnboardStatusRequest(action);

        invoke("PUT-ONBOARD_STATUS", rqProperties, null, JSONParserUtil.toJson(request));
    }

    @Override
    public void getDeviceCount(RequestCallback callback) {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        invoke("GET-TABLE_DATA-DEVICE_COUNT", rqProperties, null, null);
    }

    @Override
    public void logout() {
        List<HeaderField> rqProperties = new ArrayList<>();
        rqProperties.add(new HeaderField("Content-Type", "application/json"));

        invoke("GET-LOGOUT", rqProperties, null, null);

    }

    @Override
    protected RequestManager injectRequestManager() {
        return new ExecutorRequestManager();
    }

    @Override
    protected URLConfigManager injectURLConfigManager() {
        return new XmlV2URLConfigManager(context, "ap5630w_mobile_api_url");
    }

    @Override
    protected String interceptURLString(String url) {
        if (url.contains("{SESSION_TOKEN}") && cacheSessionToken != null) {
            url = url.replace("{SESSION_TOKEN}", cacheSessionToken);
            return url;
        }
        return null;
    }

    public void cacheSessionToken(String session_token) {
        this.cacheSessionToken = session_token;
    }

}
