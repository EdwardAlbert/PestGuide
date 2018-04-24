package cn.edu.bupt.lab805.pestguide.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */
@Entity
public class Grain {

    @Id
    private Long id;
    private String lcbm;
    private String indate;
    private String clxs;
    private String grainname;
    private String harvestdate;
    private String source;
    private Integer reserveperiod;
    private Integer innum;

    @Generated(hash = 1033144506)
    public Grain(Long id, String lcbm, String indate, String clxs, String grainname,
                 String harvestdate, String source, Integer reserveperiod,
                 Integer innum) {
        this.id = id;
        this.lcbm = lcbm;
        this.indate = indate;
        this.clxs = clxs;
        this.grainname = grainname;
        this.harvestdate = harvestdate;
        this.source = source;
        this.reserveperiod = reserveperiod;
        this.innum = innum;
    }

    @Generated(hash = 597128991)
    public Grain() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLcbm() {
        return this.lcbm;
    }

    public void setLcbm(String lcbm) {
        this.lcbm = lcbm;
    }

    public String getIndate() {
        return this.indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getClxs() {
        return this.clxs;
    }

    public void setClxs(String clxs) {
        this.clxs = clxs;
    }

    public String getGrainname() {
        return this.grainname;
    }

    public void setGrainname(String grainname) {
        this.grainname = grainname;
    }

    public String getHarvestdate() {
        return this.harvestdate;
    }

    public void setHarvestdate(String harvestdate) {
        this.harvestdate = harvestdate;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getReserveperiod() {
        return this.reserveperiod;
    }

    public void setReserveperiod(Integer reserveperiod) {
        this.reserveperiod = reserveperiod;
    }

    public Integer getInnum() {
        return this.innum;
    }

    public void setInnum(Integer innum) {
        this.innum = innum;
    }

    @Override
    public String toString() {
        return "Grain{" +
                "id=" + id +
                ", lcbm='" + lcbm + '\'' +
                ", indate='" + indate + '\'' +
                ", clxs='" + clxs + '\'' +
                ", grainname='" + grainname + '\'' +
                ", harvestdate='" + harvestdate + '\'' +
                ", source='" + source + '\'' +
                ", reserveperiod=" + reserveperiod +
                ", innum=" + innum +
                '}';
    }
}
