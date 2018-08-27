package cn.edu.bupt.lab805.pestguide.util;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
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
import cn.edu.bupt.lab805.pestguide.entity.City;
import cn.edu.bupt.lab805.pestguide.entity.Depot;
import cn.edu.bupt.lab805.pestguide.entity.Factory;
import cn.edu.bupt.lab805.pestguide.entity.Grain;
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
     * 根据害虫名称模糊查询
     *
     * @param name
     * @return
     */
    public List<Pest> queryPestBySearch(String name) {
        return pestDao.queryBuilder().where(PestDao.Properties.Name.like("%" + name + "%")).list();
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
     *
     * @return
     */
    public List<Depot> queryDepotList() {
        return depotDao.queryBuilder().list();
    }

    /**
     * 插入粮仓信息
     *
     * @param list
     */
    public void insertDepotList(List<Depot> list) {
        depotDao.insertInTx(list);
    }

    /**
     * 删除所有粮仓
     */
    public void deleteAllDepot() {
        depotDao.deleteAll();
    }

    /**
     * 通过粮仓编码查询粮仓
     *
     * @param lcbm
     * @return
     */
    public Depot queryDepotByLCBM(String lcbm) {
        return depotDao.queryBuilder().where(DepotDao.Properties.Lcbm.eq(lcbm)).unique();
    }

    /**
     * 插入粮情信息
     *
     * @param grain
     */
    public void insertGrain(Grain grain) {
        grainDao.insert(grain);
    }

    /**
     * 根据粮仓编码查询粮情
     *
     * @param lcbm
     * @return
     */
    public Grain queryGrainByLCBM(String lcbm) {
        try {
            return grainDao.queryBuilder().where(GrainDao.Properties.Lcbm.eq(lcbm)).unique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据粮仓编码删除粮情
     *
     * @param lcbm
     * @return
     */
    public void deleteGrainByLCBM(String lcbm) {
        List<Grain> list = grainDao.queryBuilder().where(GrainDao.Properties.Lcbm.eq(lcbm)).list();
        grainDao.deleteInTx(list);
    }

    /**
     * 通过城市和地区查询cityID
     *
     * @param city
     * @param district
     * @return
     */
    public String queryCityID(String city, String district) {
        List<City> cities = null;
        if (!TextUtils.isEmpty(district) && !TextUtils.isEmpty(city)) {
            cities = cityDao.queryBuilder()
                    .where(CityDao.Properties.Name.like("%" + district.substring(0, district.length() - 1) + "%"),
                            CityDao.Properties.Parent.like("%" + city.substring(0, city.length() - 1) + "%")).list();
            if (cities.size()!=0) return cities.get(0).getPosID();
        }
        if (!TextUtils.isEmpty(city)){
            cities = cityDao.queryBuilder()
                    .where(CityDao.Properties.Parent.like("%" + city.substring(0, city.length() - 1) + "%")).list();
            if (cities.size()!=0) return cities.get(0).getPosID();
        }
        return null;
    }

    /**
     * 删除登录信息
     */
    public void deleteLogininfo() {
        // TODO Auto-generated method stub
        logininfoDao.deleteAll();
    }
}
