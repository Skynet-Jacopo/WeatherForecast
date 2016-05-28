package com.example.liuqun.weatherforecast.Bean;

/**
 * Created by 90516 on 5/27/2016.
 */
public class PM25Bean {
    public String city_name;
    public String datetime;
    public String curPm;     //污染指数
    public String pm25;
    public String pm10;
    public String quality;      //污染等级
    public String des;        //详细介绍

    public PM25Bean(String city_name, String datetime, String curPm, String pm25, String pm10, String quality, String des) {
        this.city_name = city_name;
        this.datetime = datetime;
        this.curPm = curPm;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.quality = quality;
        this.des = des;
    }


}
