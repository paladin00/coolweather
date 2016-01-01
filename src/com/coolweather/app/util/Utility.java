package com.coolweather.app.util;

import android.text.TextUtils;
import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.db.CoolWeatherOpenHelper;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;
import org.w3c.dom.Text;

/**
 * Created by zhenguo on 2016/1/1.
 */
public class Utility {

    /**
     * 解析和处理服务器返回省份数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p :
                        allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);

                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c :
                        allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);

                }
                return  true;
            }
        }
        return false;
    }


    public synchronized static boolean handleCountryResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c :
                        allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountryCode(array[0]);
                    county.setCountryName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCountry(county);
                }
                return  true;
            }
        }
        return false;
    }
}