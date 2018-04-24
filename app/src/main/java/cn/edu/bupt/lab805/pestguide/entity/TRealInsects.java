package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class TRealInsects {
    @Id
    private Long id;
    private String kind;
    private String stage;
    private Integer num;
    private Long pid;

    @Generated(hash = 1237137807)
    public TRealInsects(Long id, String kind, String stage, Integer num, Long pid) {
        this.id = id;
        this.kind = kind;
        this.stage = stage;
        this.num = num;
        this.pid = pid;
    }

    @Generated(hash = 1788245617)
    public TRealInsects() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
