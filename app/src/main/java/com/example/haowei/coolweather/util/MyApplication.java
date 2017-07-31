package com.example.haowei.coolweather.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by haowei on 2017/7/30.
 */

public class MyApplication extends Application {

    private static Context context;

    /**
     * 初始化获取系统全局Context实例 + 初始化LitePal
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    /**
     * 返回全局Context
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
