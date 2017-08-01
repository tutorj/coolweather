package com.example.haowei.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.haowei.coolweather.gson.Weather;
import com.example.haowei.coolweather.util.HttpUtil;
import com.example.haowei.coolweather.util.JsonResolver;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Project_Name: CoolWeather
 * Creator: Tutorj
 * Created on: 2017/7/31 21:12
 */

public class AutoUpdateService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        updateWeather();
        //updateBingPic();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int twoHour = 2 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + twoHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 更新天气
     */
    private void updateWeather() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);

        if (weatherString != null) {
            Weather weather = JsonResolver.handleWeatherResponse(weatherString);
            String weatherId = weather.basic.weatherId;

            String weatherUrl = "http://guolin.tech/api/weather?cityid="+ weatherId + "&key=b8d8f33e9f8a47a38e384f3ef8ca764a";

            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    Weather weather = JsonResolver.handleWeatherResponse(responseText);
                    if (weather != null && weather.status.equals("ok")) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

    /**
     * 更细Bing图片
     */
    private void updateBingPic() {

        String requestBingPic = "http://guolin.tech/api/bing_pic";

        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });

    }
}
