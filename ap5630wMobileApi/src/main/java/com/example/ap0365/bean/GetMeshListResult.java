package com.example.ap0365.bean;

import java.util.List;

public class GetMeshListResult {

    public List<MeshNode> mesh_list;

    public GetMeshListResult(List<MeshNode> mesh_list) {
        this.mesh_list = mesh_list;
    }

    public static class MeshNode {
        public String name;
        public String mac;
        public int rssi; // {1~4}
        public String connect_type;
        public int mesh_mode;

        public MeshNode(String name, String mac, int rssi, String connect_type, int mesh_mode) {
            this.name = name;
            this.mac = mac;
            this.rssi = rssi;
            this.connect_type = connect_type;
            this.mesh_mode = mesh_mode;
        }
    }
}
