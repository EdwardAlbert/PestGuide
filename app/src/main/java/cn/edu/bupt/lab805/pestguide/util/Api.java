package cn.edu.bupt.lab805.pestguide.util;


import java.util.List;
import java.util.Map;

import cn.edu.bupt.lab805.pestguide.bean.Page;
import cn.edu.bupt.lab805.pestguide.bean.RealTimeData;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
import cn.edu.bupt.lab805.pestguide.entity.User;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

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
                                   @Field("source") String source, @Field("clxs") String clxs,
                                   @Field("innum") Integer innum, @Field("indate") String indate,
                                   @Field("harvestdate") String harvestdate, @Field("reserveperiod") Integer reserveperiod);

    @GET("data/sk/{id}.html")
    Observable<ResponseBody> getWheather(@Path("id") String cityId);

    @POST("app/depot/realtimedata/addRealData")
    Observable<Result> uploadData(@Body RealTimeData realTimeData);

    @POST("app/depot/realtimedata/addRealDataPic")
    @Multipart
    Observable<Result> uploadPic(@Part("username") String username, @Part("id") String id, @Part MultipartBody.Part file);

    @POST("app/depot/realtimedata/addRealDataPic")
    @Multipart
    Observable<Result> uploadPic(@PartMap Map<String, RequestBody> map);
}
