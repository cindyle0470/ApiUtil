package com.example.ap0365.bean;

public class GetTargetMeshInfoResult extends BasicResult{

    public TargetMeshInfo target_mesh_info;

    public GetTargetMeshInfoResult(String status, TargetMeshInfo target_mesh_info) {
        super(status);
        this.target_mesh_info = target_mesh_info;
    }

    public static class TargetMeshInfo {
        public String name;
        public int cpu;
        public int memory;
        public String mac;
        public String ip;
        public String auto_ip;
        public String rx; // ex: 20.3 Kbps
        public String tx; // ex: 20.3 Kbps
        public int rssi; // 1~4
        public String connect_type;
        public ConnectedPoint connect_to;
        public int mesh_mode;

        public TargetMeshInfo(String name, int cpu, int memory, String mac, String ip, String auto_ip, String rx, String tx, int rssi, String connect_type, ConnectedPoint connect_to, int mesh_mode) {
            this.name = name;
            this.cpu = cpu;
            this.memory = memory;
            this.mac = mac;
            this.ip = ip;
            this.auto_ip = auto_ip;
            this.rx = rx;
            this.tx = tx;
            this.rssi = rssi;
            this.connect_type = connect_type;
            this.connect_to = connect_to;
            this.mesh_mode = mesh_mode;
        }
    }
}
