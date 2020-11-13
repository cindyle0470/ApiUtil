package com.example.ap0365.bean;

import java.util.List;

public class GetEncryptionOptionsResult {

    public String status;
    public List<EncryptionOptions> encry_options;

    public GetEncryptionOptionsResult(String status, List<EncryptionOptions> encry_options) {
        this.status = status;
        this.encry_options = encry_options;
    }

    public static class EncryptionOptions {
        public String name;
        public int value;

        public EncryptionOptions(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
