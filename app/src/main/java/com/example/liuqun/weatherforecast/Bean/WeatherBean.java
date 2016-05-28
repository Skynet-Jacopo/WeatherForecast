package com.example.liuqun.weatherforecast.Bean;

/**
 * Created by 90516 on 5/27/2016.
 */
public class WeatherBean {
    public String direct;
    public String power;
    public String time;
    public String humidity;
    public String weather;
    public String temperature;
    public String date;
    public String city_name;
    public String week;
    public String moon;
    public int    id;

    public WeatherBean(String direct, String power, String time, String humidity,
                       String weather, String temperature, String date, String city_name,
                       String week, String moon, int id) {
        this.direct = direct;
        this.power = power;
        this.time = time;
        this.humidity = humidity;
        this.weather = weather;
        this.temperature = temperature;
        this.date = date;
        this.city_name = city_name;
        this.week = week;
        this.moon = moon;
        this.id = id;
    }


}
