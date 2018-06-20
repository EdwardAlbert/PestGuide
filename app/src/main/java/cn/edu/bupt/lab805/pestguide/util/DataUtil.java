package cn.edu.bupt.lab805.pestguide.util;

import android.util.Log;

import java.util.List;

import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.bean.Page;
import cn.edu.bupt.lab805.pestguide.bean.Result;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
import cn.edu.bupt.lab805.pestguide.entity.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    private DataUtil() {
        api = MyApplication.getInstance().getApi();
        dbHelper = DBHelper.getInstance();
    }

    public static DataUtil getInstance() {
        if (instance == null) {
            synchronized (DataUtil.class) {
                if (instance == null) {
                    instance = new DataUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取粮库信息
     */
    public void syncFactory(String username, String password) {
        Api api = MyApplication.getInstance().getApi();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     */
    public void syncUser(String username, String password) {
        Api api = MyApplication.getInstance().getApi();
        try {
            api.getUser(username, password)
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取粮仓信息
     */
    public void syncDepot(String username, String password) {
        final String user = username;
        final String pw = password;
        Api api = MyApplication.getInstance().getApi();
        try {
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
                                if (page.getTotalPages() > 1) {
                                    for (int i = 2; i <= page.getTotalPages(); i++)
                                        getDepotByPage(user, pw, i);
                                }
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据分页获取粮仓信息
     *
     * @param page
     */
    private void getDepotByPage(String username, String password, int page) {
        Api api = MyApplication.getInstance().getApi();
        try {
            api.getDepot(username, password, page, 10)
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取粮情信息
     *
     * @param username
     * @param password
     * @param lcbm
     */
    public void syncGrain(String username, String password, final String lcbm) {
        Api api = MyApplication.getInstance().getApi();
        try {
            api.getGrain(username, password, lcbm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Result<Grain>>() {
                        @Override
                        public void accept(Result<Grain> result) throws Exception {
                            if (result != null && result.getT() != null) {
                                result.getT().setLcbm(lcbm);
                                dbHelper.deleteGrainByLCBM(lcbm);
                                dbHelper.insertGrain(result.getT());
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
