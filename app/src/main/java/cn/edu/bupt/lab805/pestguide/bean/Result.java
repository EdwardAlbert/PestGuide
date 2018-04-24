package cn.edu.bupt.lab805.pestguide.bean;

/**
 * Created by zby on 2017/12/1.
 */

public class Result<T> {

    private T t;
    private Type type;
    private String content;
    private Integer id;
    private String url;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
