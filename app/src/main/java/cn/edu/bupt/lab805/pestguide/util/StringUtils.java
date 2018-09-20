package cn.edu.bupt.lab805.pestguide.util;

import android.text.TextUtils;

/**
 * Created by zby on 2018/9/20.
 */

public class StringUtils {

    public static String whichString(String str, String defaultStr) {
        if (TextUtils.isEmpty(str)) {
            return defaultStr;
        }
        return str;
    }

    public static String whichString(String str) {
        return whichString(str, "");
    }

    public static String whichString(double str) {
        return whichString(String.valueOf(str), "");
    }
}
