package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class User {

    private String username;
    private String realname;
    private String mobile;
    private String title;
    private Boolean manager;
    private Boolean hasaudit;

    @Generated(hash = 1985491031)
    public User(String username, String realname, String mobile, String title,
                Boolean manager, Boolean hasaudit) {
        this.username = username;
        this.realname = realname;
        this.mobile = mobile;
        this.title = title;
        this.manager = manager;
        this.hasaudit = hasaudit;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getManager() {
        return this.manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    public Boolean getHasaudit() {
        return this.hasaudit;
    }

    public void setHasaudit(Boolean hasaudit) {
        this.hasaudit = hasaudit;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", title='" + title + '\'' +
                ", manager=" + manager +
                ", hasaudit=" + hasaudit +
                '}';
    }
}
