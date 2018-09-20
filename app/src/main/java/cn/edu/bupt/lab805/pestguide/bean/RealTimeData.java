package cn.edu.bupt.lab805.pestguide.bean;

/**
 * Created by zby on 2017/4/24.
 */

@lombok.Data
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

}
