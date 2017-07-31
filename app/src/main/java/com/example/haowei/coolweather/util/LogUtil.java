package com.example.haowei.coolweather.util;

import android.util.Log;

/**
 * Project_Name: CoolWeather
 * Creator: Tutorj
 * Created on: 2017/7/30 20:39
 */

public class LogUtil {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    public static final int LEVEL = 1;


    /**
     * 打印verbose日志
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    /**
     * 打印debug日志
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印info日志
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印warn日志
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印error日志
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }


}
