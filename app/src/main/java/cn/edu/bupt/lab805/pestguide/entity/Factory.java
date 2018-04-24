package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by zby on 2018/4/13.
 */
@Entity
public class Factory {

    private String lkbm;
    private String lkmc;
    private String lkdz;
    private Double longtitude;
    private Double latitude;
    private Double altitude;
    private Boolean hasreal;
    private Integer totalbin;
    private String postcode;
    private String contact;
    private String phone;
    private String modifier;
    private String modifydate;
    private String lklx;
    private String pic;

    @Generated(hash = 180976524)
    public Factory(String lkbm, String lkmc, String lkdz, Double longtitude, Double latitude,
            Double altitude, Boolean hasreal, Integer totalbin, String postcode,
            String contact, String phone, String modifier, String modifydate, String lklx,
            String pic) {
        this.lkbm = lkbm;
        this.lkmc = lkmc;
        this.lkdz = lkdz;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.hasreal = hasreal;
        this.totalbin = totalbin;
        this.postcode = postcode;
        this.contact = contact;
        this.phone = phone;
        this.modifier = modifier;
        this.modifydate = modifydate;
        this.lklx = lklx;
        this.pic = pic;
    }

    @Generated(hash = 1289625573)
    public Factory() {
    }

    public String getLkbm() {
        return this.lkbm;
    }

    public void setLkbm(String lkbm) {
        this.lkbm = lkbm;
    }

    public String getLkmc() {
        return this.lkmc;
    }

    public void setLkmc(String lkmc) {
        this.lkmc = lkmc;
    }

    public String getLkdz() {
        return this.lkdz;
    }

    public void setLkdz(String lkdz) {
        this.lkdz = lkdz;
    }

    public Double getLongtitude() {
        return this.longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Boolean getHasreal() {
        return this.hasreal;
    }

    public void setHasreal(Boolean hasreal) {
        this.hasreal = hasreal;
    }

    public Integer getTotalbin() {
        return this.totalbin;
    }

    public void setTotalbin(Integer totalbin) {
        this.totalbin = totalbin;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifydate() {
        return this.modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public String getLklx() {
        return lklx;
    }

    public void setLklx(String lklx) {
        this.lklx = lklx;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "lkbm='" + lkbm + '\'' +
                ", lkmc='" + lkmc + '\'' +
                ", lkdz='" + lkdz + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                ", hasreal=" + hasreal +
                ", totalbin=" + totalbin +
                ", postcode='" + postcode + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", modifier='" + modifier + '\'' +
                ", modifydate='" + modifydate + '\'' +
                ", lklx='" + lklx + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
