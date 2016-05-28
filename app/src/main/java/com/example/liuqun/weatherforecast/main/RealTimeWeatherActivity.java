package com.example.liuqun.weatherforecast.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuqun.weatherforecast.Bean.LifeBean;
import com.example.liuqun.weatherforecast.Bean.PM25Bean;
import com.example.liuqun.weatherforecast.Bean.WeatherBean;
import com.example.liuqun.weatherforecast.R;
import com.example.liuqun.weatherforecast.adapter.LifeAdapter;
import com.example.liuqun.weatherforecast.base.MyActivity;
import com.example.liuqun.weatherforecast.utils.ImageUtil;

import java.util.List;

public class RealTimeWeatherActivity extends MyActivity {


    ListView  lv_life;
    TextView  tv_direct;
    TextView  tv_power;
    TextView  tv_time;
    TextView  tv_humidity;
    TextView  tv_weather;
    TextView  tv_temperature;
    TextView  tv_date;
    TextView  tv_city_name;
    TextView  tv_week;
    TextView  tv_moon;
    ImageView imageView;

    LifeAdapter    adapter;
    List<LifeBean> data;

    TextView tv_data_time;
    TextView tv_curpm;
    TextView tv_pm25;
    TextView tv_pm10;
    TextView tv_des;
    TextView tv_quality;

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_real_time_weather);
        lv_life = (ListView) findViewById(R.id.lv_life);
        tv_direct = (TextView) findViewById(R.id.tv_direct);
        tv_power = (TextView) findViewById(R.id.tv_power);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_humidity = (TextView) findViewById(R.id.tv_humidity);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_city_name = (TextView) findViewById(R.id.tv_city_name);
        tv_week = (TextView) findViewById(R.id.tv_week);
        tv_moon = (TextView) findViewById(R.id.tv_moon);
        imageView = (ImageView) findViewById(R.id.imageView);
        tv_data_time = (TextView) findViewById(R.id.tv_data_time);
        tv_curpm = (TextView) findViewById(R.id.tv_cur_pm);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        tv_pm10 = (TextView) findViewById(R.id.tv_pm10);
        tv_des = (TextView) findViewById(R.id.tv_des);
        tv_quality = (TextView) findViewById(R.id.tv_quality);
    }

    @Override
    public void initData() {
        super.initData();
        WeatherBean realTime = weatherUtil.getRealTime();

        tv_direct.setText(realTime.direct);
        tv_power.setText(realTime.power);
        tv_time.setText("更新时间:"+realTime.time);
        tv_humidity.setText("相对湿度:"+realTime.humidity);
        tv_weather.setText(realTime.weather);
        tv_temperature.setText("温度"+realTime.temperature+"℃");
        tv_date.setText(realTime.date);
        tv_city_name.setText(realTime.city_name);
        tv_week.setText("星期"+realTime.week);
        tv_moon.setText("农历"+realTime.moon);
        imageView.setImageResource(ImageUtil.getImageDay(realTime.id));

        PM25Bean bean =weatherUtil.getJsonPM25();

        tv_data_time.setText("更新时间"+bean.datetime);
        tv_curpm.setText("污染指数:"+bean.curPm);
        tv_pm25.setText("PM2.5:"+bean.pm25);
        tv_pm10.setText("PM10"+bean.pm10);
        tv_des.setText(bean.des);
        tv_quality.setText("污染等级:"+bean.quality);

        data =weatherUtil.getJsonLife();
        adapter =new LifeAdapter(getApplicationContext(),data);
        lv_life.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }
}
