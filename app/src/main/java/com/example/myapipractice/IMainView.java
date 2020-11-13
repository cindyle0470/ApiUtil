package com.example.myapipractice;

public interface IMainView {
    void updateOnboardStatus(boolean isOnboard);
    void toast(String msg);
    void runOnMainThread(Runnable task);
    void appFinish();
    void updateDeviceCount(int count);
}
