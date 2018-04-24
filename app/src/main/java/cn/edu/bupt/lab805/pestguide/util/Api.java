package cn.edu.bupt.lab805.pestguide.util;


import java.util.List;

import cn.edu.bupt.lab805.pestguide.bean.Page;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
import cn.edu.bupt.lab805.pestguide.entity.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zby on 2017/12/1.
 */

public interface Api {

    @POST("app/depot/login")
    @FormUrlEncoded
    Observable<Result> login(@Field("username") String username, @Field("password") String password);

    @POST("app/depot/datacollect/getlk")
    @FormUrlEncoded
    Observable<Result<Factory>> getFactory(@Field("username") String username, @Field("password") String password);

    @POST("app/depot/datacollect/getlkusers")
    @FormUrlEncoded
    Observable<Result<List<User>>> getUser(@Field("username") String username, @Field("password") String password);

    @POST("app/depot/datacollect/getlkalllc")
    @FormUrlEncoded
    Observable<Result<Page<Depot>>> getDepot(@Field("username") String username, @Field("password") String password,
                                             @Field("page") Integer page, @Field("rows") Integer rows);

    @POST("app/depot/datacollect/getLastGrain")
    @FormUrlEncoded
    Observable<Result<Grain>> getGrain(@Field("username") String username, @Field("password") String password,
                                       @Field("lcbm") String lcbm);

    @POST("app/depot/datacollect/uploadGrain")
    @FormUrlEncoded
    Observable<Result> uploadGrain(@Field("username") String username, @Field("password") String password,
                                   @Field("lcbm") String lcbm, @Field("grainname") String grainname,
                                   @Field("source") String source,@Field("clxs") String clxs,
                                   @Field("innum") Integer innum, @Field("indate") String indate,
                                   @Field("harvestdate") String harvestdate, @Field("reserveperiod") Integer reserveperiod);
}
