package com.example.haowei.coolweather.util;

import android.text.TextUtils;

import com.example.haowei.coolweather.db.City;
import com.example.haowei.coolweather.db.County;
import com.example.haowei.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

/**
 * Project_Name: CoolWeather
 * Creator: Tutorj
 * Created on: 2017/7/31 9:13
 */

public class JsonResolver {

    /**
     * 解析处理服务器响应的省级JSON数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {

        //判断响应（JSON文本）是否为空
        if (!TextUtils.isEmpty(response)) {
            try {
                //转换为JSON数组
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++) {
                    //获取JSON对象，封装后存储仅DB
                    JSONObject provinceObject = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器响应的市级JSON数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {

        //判断响应（JSON文本）是否为空
        if (!TextUtils.isEmpty(response)) {
            try {
                //转换为JSON数组
                JSONArray cities = new JSONArray(response);
                for (int i = 0; i < cities.length(); i++) {
                    //获取JSON对象，封装后存储仅DB
                    JSONObject cityObject = cities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器响应的县级JSON数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) {

        //判断响应（JSON文本）是否为空
        if (!TextUtils.isEmpty(response)) {
            try {
                //转换为JSON数组
                JSONArray counties = new JSONArray(response);
                for (int i = 0; i < counties.length(); i++) {
                    //获取JSON对象，封装后存储仅DB
                    JSONObject countyObject = counties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
