package com.example.ap0365.bean;

public class GetDeviceCountResult extends BasicResult{
    /**
     * "status": "success",
     *     "mesh_dev_count":int,
     *     "client_dev_count":int,
     *     "parental_control_count":int
     */

    public int mesh_dev_count;
    public int client_dev_count;
    public int parental_control_count;

    public GetDeviceCountResult(String status, int mesh_dev_count, int client_dev_count, int parental_control_count) {
        super(status);
        this.mesh_dev_count = mesh_dev_count;
        this.client_dev_count = client_dev_count;
        this.parental_control_count = parental_control_count;
    }
}
