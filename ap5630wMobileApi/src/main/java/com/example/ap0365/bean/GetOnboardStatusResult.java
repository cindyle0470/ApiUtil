package com.example.ap0365.bean;

public class GetOnboardStatusResult {
    public boolean onboarded;
    public String default_pwd;
    public String status;

    public GetOnboardStatusResult(boolean onboarded, String default_pwd, String status) {
        this.onboarded = onboarded;
        this.default_pwd = default_pwd;
        this.status = status;
    }
}
