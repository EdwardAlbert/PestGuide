package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */
@Entity
public class City {

    @Id
    private Long id;
    private String root;
    private String parent;
    private String name;
    private String pinyin;
    private String phone_code;
    private String area_code;
    private String x;
    private String y;
    private String posID;
    private String url;

    @Generated(hash = 707262533)
    public City(Long id, String root, String parent, String name, String pinyin,
                String phone_code, String area_code, String x, String y, String posID,
                String url) {
        this.id = id;
        this.root = root;
        this.parent = parent;
        this.name = name;
        this.pinyin = pinyin;
        this.phone_code = phone_code;
        this.area_code = area_code;
        this.x = x;
        this.y = y;
        this.posID = posID;
        this.url = url;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoot() {
        return this.root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPhone_code() {
        return this.phone_code;
    }

    public void setPhone_code(String phone_code) {
        this.phone_code = phone_code;
    }

    public String getArea_code() {
        return this.area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getX() {
        return this.x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return this.y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getPosID() {
        return this.posID;
    }

    public void setPosID(String posID) {
        this.posID = posID;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
