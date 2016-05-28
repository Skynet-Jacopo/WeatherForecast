package com.example.liuqun.weatherforecast.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuqun.weatherforecast.Bean.WeatherInfoBean;
import com.example.liuqun.weatherforecast.R;
import com.example.liuqun.weatherforecast.base.MyActivity;
import com.example.liuqun.weatherforecast.utils.ImageUtil;
import com.example.liuqun.weatherforecast.utils.WeatherUtil;

public class WeatherWeekActivity extends MyActivity {

    TextView date;
    TextView weather_day;
    TextView temperature_day;
    TextView direct_day;
    TextView power_day;
    TextView sun_up;
    TextView weather_night;
    TextView temperature_night;
    TextView direct_night;
    TextView power_night;
    TextView sun_down;
    TextView week;
    TextView moon;

    ImageView id_day;
    ImageView id_night;

    int id;//putExtra过来的KEY


    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_weather_week);
        id=getIntent().getIntExtra("id",0);//获取对应天气item的id值
        date= (TextView) findViewById(R.id.tv_date);
        weather_day= (TextView) findViewById(R.id.tv_weather_day);
        temperature_day= (TextView) findViewById(R.id.tv_temperature_day);
        direct_day= (TextView) findViewById(R.id.tv_direct_day);
        power_day= (TextView) findViewById(R.id.tv_power_day);
        sun_up= (TextView) findViewById(R.id.tv_sun_up);
        weather_night= (TextView) findViewById(R.id.tv_weather_night);
        temperature_night= (TextView) findViewById(R.id.tv_temperature_night);
        direct_night= (TextView) findViewById(R.id.tv_direct_night);
        power_night= (TextView) findViewById(R.id.tv_power_night);
        sun_down= (TextView) findViewById(R.id.tv_sun_down);
        week= (TextView) findViewById(R.id.tv_week);
        moon= (TextView) findViewById(R.id.tv_moon);
        id_day= (ImageView) findViewById(R.id.iv_image_day);
        id_night= (ImageView) findViewById(R.id.iv_image_night);
    }

    @Override
    public void initData() {
        super.initData();
        WeatherInfoBean data =weatherUtil.getJsonWeather().get(id);
        date.setText(data.date);
        weather_day.setText(data.weather_day);
        temperature_day.setText(data.temperature_day+"℃");
        direct_day.setText(data.direct_day);
        power_day.setText(data.power_day);
        sun_up.setText("日出时间:"+data.sun_up);
        weather_night.setText(data.weather_night);
        temperature_night.setText(data.temperature_night+"℃");
        direct_night.setText(data.direct_night);
        power_night.setText(data.power_night);
        sun_down.setText("日落时间"+data.sun_down);
        week.setText("星期"+data.week);
        moon.setText("农历"+data.moon);
        id_day.setImageResource(ImageUtil.getImageDay(data.id_day));
        id_night.setImageResource(ImageUtil.getImageNight(data.id_night));
    }
}
