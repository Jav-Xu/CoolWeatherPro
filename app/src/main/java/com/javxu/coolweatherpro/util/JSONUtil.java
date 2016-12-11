package com.javxu.coolweatherpro.util;

import com.google.gson.Gson;
import com.javxu.coolweatherpro.database.City;
import com.javxu.coolweatherpro.database.County;
import com.javxu.coolweatherpro.database.Province;
import com.javxu.coolweatherpro.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jav-Xu on 2016/12/11.
 */

public class JSONUtil {
    /**
     * 解析处理省级数据，将JSON数据转化成province对象存入数据库省表
     *
     * @param response 省级数据 [{"id":1,"name":"北京"},...,{"id":34,"name":"新疆"}]
     * @return 是否成功处理
     */
    public static boolean handleProvinceResponse(String response) {
        if (response != null) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.setProvinceName(jsonObject.getString("name"));
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
     * 解析处理市级数据，将JSON数据转化成province对象存入数据库省表
     *
     * @param response 省级数据 [{"id":113,"name":"南京"},...,{"id":125,"name":"宿迁"}]
     * @return 是否成功处理
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (response != null) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
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
     * 解析处理省级数据，将JSON数据转化成province对象存入数据库省表
     *
     * @param response 省级数据 [{"id":937,"name":"苏州","weather_id":"CN101190401"},...,
     *                 {"id":941,"name":"吴中","weather_id":"CN101190491"}]
     * @return 是否成功处理
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (response != null) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
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

    public static Weather handleWeatherResponse(String reponse) {
        try {
            JSONObject jsonObject = new JSONObject(reponse);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            Weather weather = new Gson().fromJson(weatherContent, Weather.class);
            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }
}
