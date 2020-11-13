package com.example.ap0365.bean;

import java.util.List;

public class GetClientListResult extends BasicResult{

    public List<Client> client_list;

    public GetClientListResult(String status, List<Client> client_list) {
        super(status);
        this.client_list = client_list;
    }

    public static class Client {
        public String name;
        public int rssi; // {1~4}
        public String mac;
        public String connect_type;
        public int index;

        public Client(String name, int rssi, String mac, String connect_type, int index) {
            this.name = name;
            this.rssi = rssi;
            this.mac = mac;
            this.connect_type = connect_type;
            this.index = index;
        }


    }

}
