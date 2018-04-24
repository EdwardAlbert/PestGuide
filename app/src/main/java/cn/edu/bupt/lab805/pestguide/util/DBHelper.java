package cn.edu.bupt.lab805.pestguide.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import cn.edu.bupt.lab805.pestguide.application.MyApplication;
import cn.edu.bupt.lab805.pestguide.dao.ActionDao;
import cn.edu.bupt.lab805.pestguide.dao.CityDao;
import cn.edu.bupt.lab805.pestguide.dao.DaoSession;
import cn.edu.bupt.lab805.pestguide.dao.DepotDao;
import cn.edu.bupt.lab805.pestguide.dao.FactoryDao;
import cn.edu.bupt.lab805.pestguide.dao.GrainDao;
import cn.edu.bupt.lab805.pestguide.dao.LogininfoDao;
import cn.edu.bupt.lab805.pestguide.dao.PestDao;
import cn.edu.bupt.lab805.pestguide.dao.TRealInsectsDao;
import cn.edu.bupt.lab805.pestguide.dao.UploadDao;
import cn.edu.bupt.lab805.pestguide.dao.UserDao;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Logininfo;
import cn.edu.bupt.lab805.pestguide.entity.Pest;
import cn.edu.bupt.lab805.pestguide.entity.User;

/**
 * Created by zby on 2018/4/13.
 */

public class DBHelper {

    private static final String TAG = "DBHelper";

    private static volatile DBHelper instance;
    private SQLiteDatabase database;
    private DaoSession daoSession;

    private ActionDao actionDao;
    private CityDao cityDao;
    private DepotDao depotDao;
    private FactoryDao factoryDao;
    private GrainDao grainDao;
    private LogininfoDao logininfoDao;
    private PestDao pestDao;
    private TRealInsectsDao tRealInsectsDao;
    private UploadDao uploadDao;
    private UserDao userDao;

    private DBHelper() {
        daoSession = MyApplication.getInstance().getDaoSession();
        database = MyApplication.getInstance().getDatabase();
        actionDao = daoSession.getActionDao();
        cityDao = daoSession.getCityDao();
        depotDao = daoSession.getDepotDao();
        factoryDao = daoSession.getFactoryDao();
        grainDao = daoSession.getGrainDao();
        logininfoDao = daoSession.getLogininfoDao();
        pestDao = daoSession.getPestDao();
        tRealInsectsDao = daoSession.getTRealInsectsDao();
        uploadDao = daoSession.getUploadDao();
        userDao = daoSession.getUserDao();
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public Pest selectPestById(Long id) {
        return pestDao.queryBuilder().where(PestDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 查询登录信息
     *
     * @return 登录用户
     */
    public Logininfo queryLogininfo() {
        try {
            return logininfoDao.queryBuilder().list().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 插入登录信息
     *
     * @param logininfo 登录信息
     */
    public void insertLogininfo(Logininfo logininfo) {
        logininfoDao.insert(logininfo);
    }

    /**
     * 删除粮库信息
     */
    public void deleteFactory() {
        factoryDao.deleteAll();
    }

    /**
     * 插入粮库信息
     *
     * @param factory
     */
    public void insertFactory(Factory factory) {
        factoryDao.insert(factory);
    }

    /**
     * 删除所有用户
     */
    public void deleteAllUser() {
        userDao.deleteAll();
    }

    /**
     * 插入用户列表
     *
     * @param users
     */
    public void insertListUsers(List<User> users) {
        userDao.insertInTx(users);
    }

    /**
     * 获取害虫列表
     *
     * @return
     */
    public List<Pest> queryPestList() {
        return pestDao.queryBuilder().list();
    }

    /**
     * 根据ID获取pest
     *
     * @param id
     * @return
     */
    public Pest queryPestById(Long id) {
        return pestDao.queryBuilder().where(PestDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 获取粮库信息
     *
     * @return
     */
    public Factory queryFactory() {
        try {
            return factoryDao.queryBuilder().list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据username查询用户
     *
     * @param username
     * @return
     */
    public User queryUserByUsername(String username) {
        try {
            return userDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).unique();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取粮仓列表
     * @return
     */
    public List<Depot> queryDepotList(){
        return depotDao.queryBuilder().list();
    }

    /**
     * 插入粮仓信息
     * @param list
     */
    public void insertDepotList(List<Depot> list){
        depotDao.insertInTx(list);
    }

    /**
     * 删除所有粮仓
     */
    public void deleteAllDepot(){
        depotDao.deleteAll();
    }
}
