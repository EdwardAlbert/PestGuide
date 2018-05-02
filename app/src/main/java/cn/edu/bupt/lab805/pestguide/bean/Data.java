package cn.edu.bupt.lab805.pestguide.bean;

import java.util.List;

/**
 * Created by zby on 2017/4/24.
 */

public class Data {

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
    private String source;
    private String trapsource;
    private String note;
    private List<RealInsects> realInsects;

    public Data(Float temperature, Float humidity, Integer x, Double longtitude, Double latitude,
                String collecttime, String source, String trapsource, List<RealInsects> realInsects) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.x = x;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.collecttime = collecttime;
        this.source = source;
        this.trapsource = trapsource;
        this.realInsects = realInsects;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getCo2() {
        return co2;
    }

    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    public Float getO2() {
        return o2;
    }

    public void setO2(Float o2) {
        this.o2 = o2;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(String collecttime) {
        this.collecttime = collecttime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTrapsource() {
        return trapsource;
    }

    public void setTrapsource(String trapsource) {
        this.trapsource = trapsource;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RealInsects> getRealInsects() {
        return realInsects;
    }

    public void setRealInsects(List<RealInsects> realInsects) {
        this.realInsects = realInsects;
    }
}
