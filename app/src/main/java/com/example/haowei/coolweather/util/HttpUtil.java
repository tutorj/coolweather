package com.example.haowei.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Project_Name: CoolWeather
 * Creator: Tutorj
 * Created on: 2017/7/31 9:08
 */

public class HttpUtil {

    /**
     * okhttp3向服务器发送请求
     * @param address
     * @param callback
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
