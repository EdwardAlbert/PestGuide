package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class Depot {
    private String lcbm;
    private String binname;
    private String orientation;
    private String granarynum;
    private String typebin;
    private Integer capacity;
    private String structureofbody;
    private String structureofroof;
    private Integer designcapacity;
    private Float designgrainheapheight;
    private Integer longth;
    private Integer width;
    private Integer height;
    private String circulatedevice;
    private String circulatefan;
    private String fanway;
    private String elsedevice;
    private String contract;
    private String phone;
    private String note;
    private String modifer;
    private String modifydate;

    @Generated(hash = 1087403090)
    public Depot(String lcbm, String binname, String orientation, String granarynum,
                 String typebin, Integer capacity, String structureofbody,
                 String structureofroof, Integer designcapacity,
                 Float designgrainheapheight, Integer longth, Integer width,
                 Integer height, String circulatedevice, String circulatefan,
                 String fanway, String elsedevice, String contract, String phone,
                 String note, String modifer, String modifydate) {
        this.lcbm = lcbm;
        this.binname = binname;
        this.orientation = orientation;
        this.granarynum = granarynum;
        this.typebin = typebin;
        this.capacity = capacity;
        this.structureofbody = structureofbody;
        this.structureofroof = structureofroof;
        this.designcapacity = designcapacity;
        this.designgrainheapheight = designgrainheapheight;
        this.longth = longth;
        this.width = width;
        this.height = height;
        this.circulatedevice = circulatedevice;
        this.circulatefan = circulatefan;
        this.fanway = fanway;
        this.elsedevice = elsedevice;
        this.contract = contract;
        this.phone = phone;
        this.note = note;
        this.modifer = modifer;
        this.modifydate = modifydate;
    }

    @Generated(hash = 1195530619)
    public Depot() {
    }

    public String getLcbm() {
        return this.lcbm;
    }

    public void setLcbm(String lcbm) {
        this.lcbm = lcbm;
    }

    public String getBinname() {
        return this.binname;
    }

    public void setBinname(String binname) {
        this.binname = binname;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getGranarynum() {
        return this.granarynum;
    }

    public void setGranarynum(String granarynum) {
        this.granarynum = granarynum;
    }

    public String getTypebin() {
        return this.typebin;
    }

    public void setTypebin(String typebin) {
        this.typebin = typebin;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStructureofbody() {
        return this.structureofbody;
    }

    public void setStructureofbody(String structureofbody) {
        this.structureofbody = structureofbody;
    }

    public String getStructureofroof() {
        return this.structureofroof;
    }

    public void setStructureofroof(String structureofroof) {
        this.structureofroof = structureofroof;
    }

    public Integer getDesigncapacity() {
        return this.designcapacity;
    }

    public void setDesigncapacity(Integer designcapacity) {
        this.designcapacity = designcapacity;
    }

    public Float getDesigngrainheapheight() {
        return this.designgrainheapheight;
    }

    public void setDesigngrainheapheight(Float designgrainheapheight) {
        this.designgrainheapheight = designgrainheapheight;
    }

    public Integer getLongth() {
        return this.longth;
    }

    public void setLongth(Integer longth) {
        this.longth = longth;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCirculatedevice() {
        return this.circulatedevice;
    }

    public void setCirculatedevice(String circulatedevice) {
        this.circulatedevice = circulatedevice;
    }

    public String getCirculatefan() {
        return this.circulatefan;
    }

    public void setCirculatefan(String circulatefan) {
        this.circulatefan = circulatefan;
    }

    public String getFanway() {
        return this.fanway;
    }

    public void setFanway(String fanway) {
        this.fanway = fanway;
    }

    public String getElsedevice() {
        return this.elsedevice;
    }

    public void setElsedevice(String elsedevice) {
        this.elsedevice = elsedevice;
    }

    public String getContract() {
        return this.contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getModifer() {
        return this.modifer;
    }

    public void setModifer(String modifer) {
        this.modifer = modifer;
    }

    public String getModifydate() {
        return this.modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "lcbm='" + lcbm + '\'' +
                ", binname='" + binname + '\'' +
                ", orientation='" + orientation + '\'' +
                ", granarynum='" + granarynum + '\'' +
                ", typebin='" + typebin + '\'' +
                ", capacity=" + capacity +
                ", structureofbody='" + structureofbody + '\'' +
                ", structureofroof='" + structureofroof + '\'' +
                ", designcapacity=" + designcapacity +
                ", designgrainheapheight=" + designgrainheapheight +
                ", longth=" + longth +
                ", width=" + width +
                ", height=" + height +
                ", circulatedevice='" + circulatedevice + '\'' +
                ", circulatefan='" + circulatefan + '\'' +
                ", fanway='" + fanway + '\'' +
                ", elsedevice='" + elsedevice + '\'' +
                ", contract='" + contract + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", modifer='" + modifer + '\'' +
                ", modifydate='" + modifydate + '\'' +
                '}';
    }
}
