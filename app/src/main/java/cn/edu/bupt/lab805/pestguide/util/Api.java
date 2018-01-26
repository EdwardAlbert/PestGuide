package cn.edu.bupt.lab805.pestguide.util;


import cn.edu.bupt.lab805.pestguide.bean.LoginBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zby on 2017/12/1.
 */

public interface Api {

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("username") String username,@Field("password") String password);
}
