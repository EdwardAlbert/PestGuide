package cn.edu.bupt.lab805.pestguide.util;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 用法：
 * 在Activity的oncreat方法里面加上ActivityManager.getInstance().addActivity(this);
 * 退出时调用：ActivityManager.getInstance().exit();
 */
public class ActivityManager {

    private List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}