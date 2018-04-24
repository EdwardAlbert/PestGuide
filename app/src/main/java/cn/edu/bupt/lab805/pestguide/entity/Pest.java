package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class Pest {

    @Id
    private Long id;
    private String name;
    private Short classicid;
    private String family;
    private String category;
    private String feature;
    private String habit;
    private String damage;
    private String distribution;
    private String prevention;
    private String pictureurl;
    private Integer importance;

    @Generated(hash = 407831763)
    public Pest(Long id, String name, Short classicid, String family,
                String category, String feature, String habit, String damage,
                String distribution, String prevention, String pictureurl,
                Integer importance) {
        this.id = id;
        this.name = name;
        this.classicid = classicid;
        this.family = family;
        this.category = category;
        this.feature = feature;
        this.habit = habit;
        this.damage = damage;
        this.distribution = distribution;
        this.prevention = prevention;
        this.pictureurl = pictureurl;
        this.importance = importance;
    }

    @Generated(hash = 495447472)
    public Pest() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getClassicid() {
        return this.classicid;
    }

    public void setClassicid(Short classicid) {
        this.classicid = classicid;
    }

    public String getFamily() {
        return this.family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getHabit() {
        return this.habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getDamage() {
        return this.damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDistribution() {
        return this.distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getPrevention() {
        return this.prevention;
    }

    public void setPrevention(String prevention) {
        this.prevention = prevention;
    }

    public String getPictureurl() {
        return this.pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public Integer getImportance() {
        return this.importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Pest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classicid=" + classicid +
                ", family='" + family + '\'' +
                ", category='" + category + '\'' +
                ", feature='" + feature + '\'' +
                ", habit='" + habit + '\'' +
                ", damage='" + damage + '\'' +
                ", distribution='" + distribution + '\'' +
                ", prevention='" + prevention + '\'' +
                ", pictureurl='" + pictureurl + '\'' +
                ", importance=" + importance +
                '}';
    }
}
