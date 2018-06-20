package cn.edu.bupt.lab805.pestguide.application;

import android.app.Application;
import android.app.Service;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import cn.edu.bupt.lab805.pestguide.dao.DaoMaster;
import cn.edu.bupt.lab805.pestguide.dao.DaoSession;
import cn.edu.bupt.lab805.pestguide.service.LocationService;
import cn.edu.bupt.lab805.pestguide.util.Api;
import cn.edu.bupt.lab805.pestguide.util.JsonUtils;
import cn.edu.bupt.lab805.pestguide.util.UrlContainer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by zby on 2018/4/13.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    //数据库
    private static final String DATABASE_PATH = "/data/data/cn.edu.bupt.lab805.pestguide/";
    private static final String DATABASE_NAME = "notes.db";
    private SQLiteDatabase database;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    //网络连接
    private Retrofit retrofit;
    private Api api;
    //定位
    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null)
            mInstance = this;
        //网络连接初始化
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlContainer.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(JsonUtils.getMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    /**
     * 获取Api对象
     *
     * @return
     */
    public Api getApi() {
        return api;
    }

    /**
     * 数据库初始化
     */
    private DaoMaster initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.this,
                DATABASE_PATH + DATABASE_NAME, null);
        database = helper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        return daoMaster;
    }

    public SQLiteDatabase getDatabase() {
        if (database == null) {
            initDatabase();
        }
        return database;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            initDatabase();
        }
        return daoSession;
    }
}
