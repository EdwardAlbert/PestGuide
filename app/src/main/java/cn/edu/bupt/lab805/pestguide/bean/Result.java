package cn.edu.bupt.lab805.pestguide.bean;

/**
 * Created by zby on 2017/12/1.
 */
@lombok.Data
public class Result<T> {

    private T t;
    private Type type;
    private String content;
    private Long id;
    private String url;


    @Override
    public String toString() {
        return "Result{" +
                "t=" + t +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
