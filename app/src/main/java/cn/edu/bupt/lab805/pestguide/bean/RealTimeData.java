package cn.edu.bupt.lab805.pestguide.bean;

/**
 * Created by zby on 2017/4/24.
 */

public class RealTimeData {
    private String username;
    private String lcbm;
    private String deviceno;
    private Data data;

    public RealTimeData(String username, String lcbm, Data data) {
        this.username = username;
        this.lcbm = lcbm;
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLcbm() {
        return lcbm;
    }

    public void setLcbm(String lcbm) {
        this.lcbm = lcbm;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
