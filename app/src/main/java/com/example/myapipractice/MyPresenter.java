package com.example.myapipractice;

import android.content.Context;

import com.example.ap0365.Ap5630wApi;
import com.example.ap0365.bean.ErrorResult;
import com.example.ap0365.bean.GetDeviceCountResult;
import com.example.ap0365.bean.GetOnboardStatusResult;
import com.example.ap0365.bean.LoginResult;

import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.response.Response;
import crop.computer.askey.androidlib.util.JSONParserUtil;

public class MyPresenter {
    private IMainView mainView;
    private Context context;
    private Ap5630wApi api;

    public MyPresenter(final IMainView mainView, final Context context) {
        this.mainView = mainView;
        this.context = context;
        api = new Ap5630wApi(context.getApplicationContext());
    }

    public void logout() {
        api.logout();
        mainView.appFinish();
    }

    public void setOnboardStatus(String status) {
        api.setOnboardStatus(status, new RequestCallback() {
            @Override
            public void onSuccess(String key, Response response, String content) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainView.updateOnboardStatus(true);
                    }
                });
            }

            @Override
            public void onFail(String key, Response response, int errorType, String errorMessage) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainView.updateOnboardStatus(false);
                        ErrorResult errorResult = JSONParserUtil.fromJson(errorMessage, ErrorResult.class);
                        String setMessage = errorResult.errorMessage;

                        mainView.toast(setMessage);
                    }
                });
            }
        });
    }

    public void getOnboardStatus() {
        api.getOnboardStatus(new RequestCallback() {
            @Override
            public void onSuccess(String key, Response response, String content) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GetOnboardStatusResult result = JSONParserUtil.fromJson(content, GetOnboardStatusResult.class);
                        boolean isOnboard = result.onboarded;
                        mainView.updateOnboardStatus(isOnboard);
                    }
                });
            }

            @Override
            public void onFail(String key, Response response, int errorType, String errorMessage) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ErrorResult errorResult = JSONParserUtil.fromJson(errorMessage, ErrorResult.class);
                        String setMessage = errorResult.errorMessage;

                        mainView.toast(setMessage);
                    }
                });
            }
        });
    }

    public void getDeviceCount() {
        api.getDeviceCount(new RequestCallback() {
            @Override
            public void onSuccess(String key, Response response, String content) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GetDeviceCountResult result = JSONParserUtil.fromJson(content, GetDeviceCountResult.class);
                        int deviceCount = result.mesh_dev_count;
                        mainView.updateDeviceCount(deviceCount);
                    }
                });
            }

            @Override
            public void onFail(String key, Response response, int errorType, String errorMessage) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ErrorResult errorResult = JSONParserUtil.fromJson(errorMessage, ErrorResult.class);
                        String setMessage = errorResult.errorMessage;

                        mainView.toast(setMessage);
                    }
                });
            }
        });
    }

    public void changePsw(String psw) {
        api.setAccountPassword(psw, new RequestCallback() {
            @Override
            public void onSuccess(String key, Response response, String content) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainView.toast("密碼變更成功");
                    }
                });
            }

            @Override
            public void onFail(String key, Response response, int errorType, String errorMessage) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ErrorResult errorResult = JSONParserUtil.fromJson(errorMessage, ErrorResult.class);
                                String setMessage = errorResult.errorMessage;

                                mainView.toast(setMessage);
                            }
                        });
                    }
                });
            }
        });
    }

    public void login(String name, String psw){
        api.login(name, psw, new RequestCallback() {
            @Override
            public void onSuccess(String key, Response response, String content) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginResult result= JSONParserUtil.fromJson(content, LoginResult.class);
                        String login_status = result.status;
                        String token = result.token;
                        if(login_status.equals("success")) {
                            mainView.toast("已登入");
                            api.cacheSessionToken(token);
                        } else {
                            mainView.toast("登入失敗");
                        }
                    }
                });
            }

            @Override
            public void onFail(String key, Response response, int errorType, String errorMessage) {
                runUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ErrorResult errorResult = JSONParserUtil.fromJson(errorMessage, ErrorResult.class);
                            String setMessage = errorResult.errorMessage;
                            String errorCode = errorResult.errorCode;
                            switch (key) {
                                case "GET-LOGIN":
                                    mainView.toast(errorCode);
                                    break;

                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            if(errorType== Response.SOCKET_TIMEOUT_EXCEPTION_ERROR){
                                mainView.toast("Timeout");
                            }
                            else{
                                mainView.toast("Access Error");
                            }
                        }

                    }
                });
            }
        });
    }

    public void runUiThread(Runnable task) {
        if(mainView != null) {
            mainView.runOnMainThread(task);
        }
    }
}
