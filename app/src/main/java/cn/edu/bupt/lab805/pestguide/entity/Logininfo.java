package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */
@Entity
public class Logininfo {

    @Id
    private Long id;
    private String username;
    private String password;
    private String lkbm;
    private String session;

    @Generated(hash = 1173175160)
    public Logininfo(Long id, String username, String password, String lkbm,
                     String session) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lkbm = lkbm;
        this.session = session;
    }

    @Generated(hash = 1624005026)
    public Logininfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLkbm() {
        return this.lkbm;
    }

    public void setLkbm(String lkbm) {
        this.lkbm = lkbm;
    }

    public String getSession() {
        return this.session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
