package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */

@Entity
public class Action {

    @Id
    private Long id;
    private Long gybm;
    private String actionname;
    private java.util.Date startdate;
    private java.util.Date enddate;
    private String actionremark;
    private Date submittime;
    @Generated(hash = 1309988261)
    public Action(Long id, Long gybm, String actionname, java.util.Date startdate,
            java.util.Date enddate, String actionremark, Date submittime) {
        this.id = id;
        this.gybm = gybm;
        this.actionname = actionname;
        this.startdate = startdate;
        this.enddate = enddate;
        this.actionremark = actionremark;
        this.submittime = submittime;
    }
    @Generated(hash = 2056262033)
    public Action() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGybm() {
        return this.gybm;
    }
    public void setGybm(Long gybm) {
        this.gybm = gybm;
    }
    public String getActionname() {
        return this.actionname;
    }
    public void setActionname(String actionname) {
        this.actionname = actionname;
    }
    public java.util.Date getStartdate() {
        return this.startdate;
    }
    public void setStartdate(java.util.Date startdate) {
        this.startdate = startdate;
    }
    public java.util.Date getEnddate() {
        return this.enddate;
    }
    public void setEnddate(java.util.Date enddate) {
        this.enddate = enddate;
    }
    public String getActionremark() {
        return this.actionremark;
    }
    public void setActionremark(String actionremark) {
        this.actionremark = actionremark;
    }
    public Date getSubmittime() {
        return this.submittime;
    }
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}
