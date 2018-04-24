package cn.edu.bupt.lab805.pestguide.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zby on 2018/4/13.
 */
@Entity
public class Grain implements Serializable {

    private static final long serialVersionUID = 2493222255049443510L;
    @Id
    private Long id;
    private String indate;
    private String clxs;
    private String grainname;
    private String harvestdate;
    private String source;
    private Float water;
    private Float impurity;
    private Float grainheight;
    private String dryingmethod;
    private Integer reserveperiod;
    private Integer innum;
    private String container;
    private Integer empty_bin500pa;
    private Integer halfemptybin500pa;
    private Integer fullbin300pa;
    private Integer halffullbin;
    private String storetechnology;
    private String storemethod;
    private String controltemperaturemeasures;
    private String controlhumiditymeasures;
    private String modifier;
    private String modifytime;

    @Generated(hash = 355572853)
    public Grain(Long id, String indate, String clxs, String grainname,
                 String harvestdate, String source, Float water, Float impurity,
                 Float grainheight, String dryingmethod, Integer reserveperiod,
                 Integer innum, String container, Integer empty_bin500pa,
                 Integer halfemptybin500pa, Integer fullbin300pa, Integer halffullbin,
                 String storetechnology, String storemethod,
                 String controltemperaturemeasures, String controlhumiditymeasures,
                 String modifier, String modifytime) {
        this.id = id;
        this.indate = indate;
        this.clxs = clxs;
        this.grainname = grainname;
        this.harvestdate = harvestdate;
        this.source = source;
        this.water = water;
        this.impurity = impurity;
        this.grainheight = grainheight;
        this.dryingmethod = dryingmethod;
        this.reserveperiod = reserveperiod;
        this.innum = innum;
        this.container = container;
        this.empty_bin500pa = empty_bin500pa;
        this.halfemptybin500pa = halfemptybin500pa;
        this.fullbin300pa = fullbin300pa;
        this.halffullbin = halffullbin;
        this.storetechnology = storetechnology;
        this.storemethod = storemethod;
        this.controltemperaturemeasures = controltemperaturemeasures;
        this.controlhumiditymeasures = controlhumiditymeasures;
        this.modifier = modifier;
        this.modifytime = modifytime;
    }

    @Generated(hash = 597128991)
    public Grain() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndate() {
        return this.indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getClxs() {
        return this.clxs;
    }

    public void setClxs(String clxs) {
        this.clxs = clxs;
    }

    public String getGrainname() {
        return this.grainname;
    }

    public void setGrainname(String grainname) {
        this.grainname = grainname;
    }

    public String getHarvestdate() {
        return this.harvestdate;
    }

    public void setHarvestdate(String harvestdate) {
        this.harvestdate = harvestdate;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Float getWater() {
        return this.water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getImpurity() {
        return this.impurity;
    }

    public void setImpurity(Float impurity) {
        this.impurity = impurity;
    }

    public Float getGrainheight() {
        return this.grainheight;
    }

    public void setGrainheight(Float grainheight) {
        this.grainheight = grainheight;
    }

    public String getDryingmethod() {
        return this.dryingmethod;
    }

    public void setDryingmethod(String dryingmethod) {
        this.dryingmethod = dryingmethod;
    }

    public Integer getReserveperiod() {
        return this.reserveperiod;
    }

    public void setReserveperiod(Integer reserveperiod) {
        this.reserveperiod = reserveperiod;
    }

    public Integer getInnum() {
        return this.innum;
    }

    public void setInnum(Integer innum) {
        this.innum = innum;
    }

    public String getContainer() {
        return this.container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getEmpty_bin500pa() {
        return this.empty_bin500pa;
    }

    public void setEmpty_bin500pa(Integer empty_bin500pa) {
        this.empty_bin500pa = empty_bin500pa;
    }

    public Integer getHalfemptybin500pa() {
        return this.halfemptybin500pa;
    }

    public void setHalfemptybin500pa(Integer halfemptybin500pa) {
        this.halfemptybin500pa = halfemptybin500pa;
    }

    public Integer getFullbin300pa() {
        return this.fullbin300pa;
    }

    public void setFullbin300pa(Integer fullbin300pa) {
        this.fullbin300pa = fullbin300pa;
    }

    public Integer getHalffullbin() {
        return this.halffullbin;
    }

    public void setHalffullbin(Integer halffullbin) {
        this.halffullbin = halffullbin;
    }

    public String getStoretechnology() {
        return this.storetechnology;
    }

    public void setStoretechnology(String storetechnology) {
        this.storetechnology = storetechnology;
    }

    public String getStoremethod() {
        return this.storemethod;
    }

    public void setStoremethod(String storemethod) {
        this.storemethod = storemethod;
    }

    public String getControltemperaturemeasures() {
        return this.controltemperaturemeasures;
    }

    public void setControltemperaturemeasures(String controltemperaturemeasures) {
        this.controltemperaturemeasures = controltemperaturemeasures;
    }

    public String getControlhumiditymeasures() {
        return this.controlhumiditymeasures;
    }

    public void setControlhumiditymeasures(String controlhumiditymeasures) {
        this.controlhumiditymeasures = controlhumiditymeasures;
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
}
