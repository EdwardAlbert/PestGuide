package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class Upload {

    @Id
    private Long id;
    private Float temperature;
    private Float humidity;
    private Float co2;
    private Float o2;
    private String kind;
    private String stage;
    private Integer num;
    private Integer x;
    private Integer y;
    private Integer z;
    private Double longtitude;
    private Double latitude;
    private Double altitude;
    private String collecttime;
    private String modifier;
    private String modifytime;
    private String pic;
    private String source;
    private String note;
    private String trapsource;
    private String lcbm;
    private String binname;
    private String orientation;
    private String granarynum;
    @Generated(hash = 473963593)
    public Upload(Long id, Float temperature, Float humidity, Float co2, Float o2,
            String kind, String stage, Integer num, Integer x, Integer y, Integer z,
            Double longtitude, Double latitude, Double altitude, String collecttime,
            String modifier, String modifytime, String pic, String source,
            String note, String trapsource, String lcbm, String binname,
            String orientation, String granarynum) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.o2 = o2;
        this.kind = kind;
        this.stage = stage;
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.collecttime = collecttime;
        this.modifier = modifier;
        this.modifytime = modifytime;
        this.pic = pic;
        this.source = source;
        this.note = note;
        this.trapsource = trapsource;
        this.lcbm = lcbm;
        this.binname = binname;
        this.orientation = orientation;
        this.granarynum = granarynum;
    }
    @Generated(hash = 365668368)
    public Upload() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Float getTemperature() {
        return this.temperature;
    }
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
    public Float getHumidity() {
        return this.humidity;
    }
    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }
    public Float getCo2() {
        return this.co2;
    }
    public void setCo2(Float co2) {
        this.co2 = co2;
    }
    public Float getO2() {
        return this.o2;
    }
    public void setO2(Float o2) {
        this.o2 = o2;
    }
    public String getKind() {
        return this.kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getStage() {
        return this.stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public Integer getNum() {
        return this.num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Integer getX() {
        return this.x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public Integer getY() {
        return this.y;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    public Integer getZ() {
        return this.z;
    }
    public void setZ(Integer z) {
        this.z = z;
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
    public String getCollecttime() {
        return this.collecttime;
    }
    public void setCollecttime(String collecttime) {
        this.collecttime = collecttime;
    }
    public String getModifier() {
        return this.modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public String getModifytime() {
        return this.modifytime;
    }
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getTrapsource() {
        return this.trapsource;
    }
    public void setTrapsource(String trapsource) {
        this.trapsource = trapsource;
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
}
