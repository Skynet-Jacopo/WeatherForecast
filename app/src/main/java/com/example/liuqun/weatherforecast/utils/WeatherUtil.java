package com.example.liuqun.weatherforecast.utils;

import android.net.Uri;
import android.util.Log;

import com.example.liuqun.weatherforecast.Bean.LifeBean;
import com.example.liuqun.weatherforecast.Bean.PM25Bean;
import com.example.liuqun.weatherforecast.Bean.WeatherBean;
import com.example.liuqun.weatherforecast.Bean.WeatherInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90516 on 5/27/2016.
 */
public class WeatherUtil {

    private static final String TAG    = "WeatherUtil";
    public static final  String APIKEY = "0fdb111d480794e6a0c6f1bdbfba2188";
    private JSONObject jsonRealTime;
    private JSONObject jsonLife;
    private JSONArray  jsonWeather;
    private JSONObject jsonPM25;

    public WeatherUtil(String city_name) {
        getResult(city_name);
    }

    /**
     * 根据城市名获取天气数据
     *
     * @param cityname
     */
    private String getResult(String cityname) {
        String         data   = null;
        BufferedReader reader = null;

        HttpURLConnection connection = null;

        String urlStr = "http://op.juhe.cn/onebox/weather/query?";

        //拼接URL
        String uri = Uri.parse(urlStr).buildUpon()
                .appendQueryParameter("cityname", cityname)
                .appendQueryParameter("dtype", "")//返回数据类型
                .appendQueryParameter("key", APIKEY)
                .build().toString();

        Log.d(TAG, "getResult: " + uri);
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            InputStream in = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer sb = new StringBuffer();
            String       line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            data = sb.toString();
            Log.d(TAG, "getResult: data"+data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getWeatherDataFromJson(data, cityname);//解析JSon数据
        return data;
    }

    //初步解析json数据
    private void getWeatherDataFromJson(String jsonStr, String cityname) {

        try {
            JSONObject json = new JSONObject(jsonStr);

            String str  = json.getString("reason");
            int    code = json.getInt("error_code");

            if (str.equals("成功") || str.equals("successed!") || code == 0) {
                JSONObject jsonResult = json.getJSONObject("result");

                JSONObject jsonData = jsonResult.getJSONObject("data");

                jsonRealTime = jsonData.getJSONObject("realtime");//实时数据
                jsonLife = jsonData.getJSONObject("life");  //生活指数
                jsonWeather = jsonData.getJSONArray("weather");//一周天气
                jsonPM25 = jsonData.getJSONObject("pm25");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public PM25Bean getJsonPM25() {
        String city_name = null;
        String datetime  = null;
        String curPm     = null;//污染指数
        String pm25      = null;
        String pm10      = null;
        String quality   = null;//污染等级
        String des       = null;

        try {
            JSONObject json = jsonPM25.getJSONObject("pm25");
            city_name = jsonPM25.getString("cityName");
            datetime = jsonPM25.getString("dateTime");

            curPm = json.getString("curPm");
            pm25 = json.getString("pm25");
            pm10 = json.getString("pm10");
            quality = json.getString("quality");
            des = json.getString("des");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        PM25Bean bean = new PM25Bean(city_name, datetime, curPm, pm25, pm10, quality, des);
        return bean;
    }

    /**
     * 解析一周天气详细数据
     *
     * @return
     */
    public List<WeatherInfoBean> getJsonWeather() {
        List<WeatherInfoBean> beanList = new ArrayList<>();

        for (int i = 0; i < jsonWeather.length(); i++) {//for循环遍历读取信息
            String date              = null;  //日期
            String weather_day       = null;//天气
            String temperature_day   = null;  //温度
            String direct_day        = null;    // 风向
            String power_day         = null;      //风级
            String sun_up            = null; //日升
            String weather_night     = null; //天气
            String temperature_night = null; //夜间温度
            String direct_night      = null; //风向
            String power_night       = null; //风级
            String sun_down          = null; //日落
            String week              = null; //星期
            String moon              = null;  //农历日期
            int    id_day            = -1;
            int    id_night          = -1;

            try {
                JSONObject data  = jsonWeather.getJSONObject(i);
                JSONObject info  = data.getJSONObject("info");
                JSONArray  night = info.getJSONArray("night");
                JSONArray  day   = info.getJSONArray("day");
                date = data.getString("date");
                weather_day = day.getString(1);
                temperature_day = day.getString(2);
                direct_day = day.getString(3);
                power_day = day.getString(4);
                sun_up = day.getString(5);
                weather_night = night.getString(1);
                temperature_night = night.getString(2);
                direct_night = night.getString(3);
                power_night = night.getString(4);
                sun_down = night.getString(5);
                week = data.getString("week");
                moon = data.getString("nongli");
                id_day = day.getInt(0);
                id_night = night.getInt(0);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            WeatherInfoBean bean = new WeatherInfoBean(date, weather_day,
                    temperature_day, direct_day, power_day, sun_up,
                    weather_night, temperature_night, direct_night, power_night,
                    sun_down, week, moon, id_day, id_night);

            beanList.add(bean);
        }
        return beanList;
    }

    public List<LifeBean> getJsonLife() {
        LifeBean       lifeBean;
        List<LifeBean> beanList  = new ArrayList<>();
        String         kongtiao  = null;
        String         yundong   = null;
        String         ziwaixian = null;
        String         ganmao    = null;
        String         xiche     = null;
        String         wuran     = null;
        String         chuanyi   = null;

        try {
            lifeBean = getString("kongtiao");
            lifeBean.title = "空调指数";
            beanList.add(lifeBean);
            lifeBean = getString("yundong");
            lifeBean.title = "运动指数";
            beanList.add(lifeBean);
            lifeBean = getString("ziwaixian");
            lifeBean.title = "紫外线指数";
            beanList.add(lifeBean);
            lifeBean = getString("ganmao");
            lifeBean.title = "感冒指数";
            beanList.add(lifeBean);
            lifeBean = getString("xiche");
            lifeBean.title = "洗车指数";
            beanList.add(lifeBean);
            lifeBean = getString("wuran");
            lifeBean.title = "污染指数";
            beanList.add(lifeBean);
            lifeBean = getString("chuanyi");
            lifeBean.title = "穿衣指数";
            beanList.add(lifeBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * 根据key获取生活指数详细信息
     *
     * @param key
     * @return
     * @throws JSONException
     */
    private LifeBean getString(String key) throws JSONException {
        LifeBean bean;

        JSONObject info      = jsonLife.getJSONObject("info");
        JSONArray  jsonArray = info.getJSONArray(key);
        bean = new LifeBean(jsonArray.getString(0), jsonArray.getString(1));
        return bean;
    }

    /**
     * 读取实时数据
     *
     * @return
     */
    public WeatherBean getRealTime() {
        String direct      = null;
        String power       = null;
        String humidity    = null;
        String info        = null;
        String temperature = null;
        int    id          = 0;
        String time        = null;
        String date        = null;
        String city_name   = null;
        String week        = null;
        String moon        = null;
        try {
            JSONObject wind    = jsonRealTime.getJSONObject("wind");
            JSONObject weather = jsonRealTime.getJSONObject("weather");
            direct = wind.getString("direct");
            power = wind.getString("power");
            humidity = weather.getString("humidity");
            info = weather.getString("info");
            temperature = weather.getString("temperature");
            id = weather.getInt("img");
            time = jsonRealTime.getString("time");
            date = jsonRealTime.getString("date");
            city_name = jsonRealTime.getString("city_name");
            Object week_object = jsonRealTime.get("week");
            week = getWeek(week_object);
            moon = jsonRealTime.getString("moon");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        WeatherBean bean = new WeatherBean(direct, power, time, humidity, info,
                temperature, date, city_name, week, moon, id);
        return bean;
    }

    /**
     * 根据获得星期的小写转换大写
     * @param week_object
     * @return
     */
    private String getWeek(Object week_object) {
        String week =null;
        if (week_object instanceof Integer){
            int week_int = (int) week_object;
            switch (week_int){
                case 1:
                    week ="一";
                    break;
                case 2:
                    week ="二";
                    break;
                case 3:
                    week ="三";
                    break;
                case 4:
                    week ="四";
                    break;
                case 5:
                    week ="五";
                    break;
                case 6:
                    week ="六";
                    break;
                case 7:
                    week ="日";
                    break;
            }
        }else {
            week =week_object.toString();
        }
        return week;
    }
}
