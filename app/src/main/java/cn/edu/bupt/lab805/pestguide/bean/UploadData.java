package cn.edu.bupt.lab805.pestguide.bean;

import java.util.List;

/**
 * Created by zby on 2018/9/18.
 */
@lombok.Data
public class UploadData {

    private Long id;
    private Double temperature;
    private Double humidity;
    private String kind;
    private String stage;
    private Integer num;
    private Integer x;
    private Integer y;
    private Integer z;
    private Double longtitude;
    private Double latitude;
    private String collecttime;
    private String modifier;
    private String modifytime;
    private List<RealInsects> realInsects;
    private List<RealDataPic> realdataPics;
    private String source;
    private String note;
    private String trapsource;
    private Tgrainbin tgrainbin;

}
