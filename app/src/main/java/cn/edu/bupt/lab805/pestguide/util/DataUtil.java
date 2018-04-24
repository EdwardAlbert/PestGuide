package cn.edu.bupt.lab805.pestguide.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Page;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.User;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zby on 2018/4/24.
 */

public class DataUtil {

    private static final String TAG = "DataUtil";

    private static volatile DataUtil instance;
    private Api api;
    private DBHelper dbHelper;

    private DataUtil(MyApplication context) {
        api = context.getApi();
        dbHelper = DBHelper.getInstance();
    }

    public static DataUtil getInstance(MyApplication context) {
        if (instance == null) {
            synchronized (DataUtil.class) {
                if (instance == null) {
                    instance = new DataUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获取粮库信息
     */
    public void syncFactory(String username,String password) {
        Api api = MyApplication.getInstance().getApi();
        api.getFactory(username, password)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result<Factory>>() {
                    @Override
                    public void accept(Result<Factory> result) throws Exception {
                        if (result != null && result.getT() != null) {
                            dbHelper.deleteFactory();
                            dbHelper.insertFactory(result.getT());
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     */
    public void syncUser(String username,String password) {
        Api api = MyApplication.getInstance().getApi();
        api.getUser(username,password)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result<List<User>>>() {
                    @Override
                    public void accept(Result<List<User>> result) throws Exception {
                        if (result != null && result.getT() != null) {
                            dbHelper.deleteAllUser();
                            dbHelper.insertListUsers(result.getT());
                        }
                    }
                });
    }

    /**
     * 获取粮仓信息
     */
    public void syncDepot(final String username, final String password) {
        Api api = MyApplication.getInstance().getApi();
        api.getDepot(username, password, 1, 10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result<Page<Depot>>>() {
                    @Override
                    public void accept(Result<Page<Depot>> result) throws Exception {
                        if (result != null && result.getT() != null) {
                            Page<Depot> page = result.getT();
                            if (page.getRows() != null && page.getRows().size() > 0) {
                                Log.d(TAG, "accept: " + page.getRows());
                                dbHelper.deleteAllDepot();
                                dbHelper.insertDepotList(page.getRows());
                            }
                            if (page.getTotalPages()>1) {
                                for (int i = 2; i <= page.getTotalPages(); i++)
                                    getDepotByPage(username,password,i);
                            }
                        }
                    }
                });
    }

    /**
     * 根据分页获取粮仓信息
     *
     * @param page
     */
    private void getDepotByPage(String username,String password,int page) {
        Api api = MyApplication.getInstance().getApi();
        api.getDepot(username,password, page, 10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Result<Page<Depot>>>() {
                    @Override
                    public void accept(Result<Page<Depot>> result) throws Exception {
                        if (result != null && result.getT() != null) {
                            Page<Depot> page = result.getT();
                            if (page.getRows() != null && page.getRows().size() > 0) {
                                dbHelper.insertDepotList(page.getRows());
                            }
                        }
                    }
                });
    }
}
