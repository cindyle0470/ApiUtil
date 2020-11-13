package com.example.ap0365.bean;

public class LoginResult {
    public String status;
    public String token;

    public LoginResult(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
