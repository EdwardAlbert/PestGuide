package cn.edu.bupt.lab805.pestguide.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zby on 2017/4/22.
 */

public class RealInsects implements Parcelable {

    private String kind;
    private Integer num;
    private String stage;

    public RealInsects() {
    }

    public RealInsects(String kind, Integer num) {
        this.kind = kind;
        this.num = num;
    }

    protected RealInsects(Parcel in) {
        kind = in.readString();
        if (in.readByte() == 0) {
            num = null;
        } else {
            num = in.readInt();
        }
        stage = in.readString();
    }

    public static final Creator<RealInsects> CREATOR = new Creator<RealInsects>() {
        @Override
        public RealInsects createFromParcel(Parcel in) {
            return new RealInsects(in);
        }

        @Override
        public RealInsects[] newArray(int size) {
            return new RealInsects[size];
        }
    };

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "RealInsects{" +
                "kind='" + kind + '\'' +
                ", num=" + num +
                ", stage='" + stage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        if (num == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(num);
        }
        dest.writeString(stage);
    }
}
