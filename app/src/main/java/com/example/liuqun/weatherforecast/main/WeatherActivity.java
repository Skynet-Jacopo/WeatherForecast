package com.example.liuqun.weatherforecast.main;


import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liuqun.weatherforecast.Bean.WeatherBean;
import com.example.liuqun.weatherforecast.Bean.WeatherInfoBean;
import com.example.liuqun.weatherforecast.R;
import com.example.liuqun.weatherforecast.adapter.WeatherAdapter;
import com.example.liuqun.weatherforecast.base.MyActivity;
import com.example.liuqun.weatherforecast.utils.ImageUtil;
import com.example.liuqun.weatherforecast.utils.WeatherUtil;



import java.util.List;

public class WeatherActivity extends MyActivity {

    ListView       lv_weather;
    RelativeLayout rl_real;
    TextView       tv_direct;
    TextView       tv_power;
    TextView       tv_time;//更新时间
    TextView       tv_humidity;
    TextView       tv_weather;
    TextView       tv_temperature;
    TextView       tv_date;
    TextView       tv_city_name;
    TextView       tv_week;
    TextView       tv_moon;

    ImageView      imageView;
    WeatherAdapter adapter;


    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_weather);
        lv_weather= (ListView) findViewById(R.id.lv_weather);
        rl_real = (RelativeLayout) findViewById(R.id.rl_real);
        tv_direct= (TextView) findViewById(R.id.tv_direct);
        tv_power= (TextView) findViewById(R.id.tv_power);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_humidity= (TextView) findViewById(R.id.tv_humidity);
        tv_weather= (TextView) findViewById(R.id.tv_weather);
        tv_temperature= (TextView) findViewById(R.id.tv_temperature);
        tv_date= (TextView) findViewById(R.id.tv_date);
        tv_city_name= (TextView) findViewById(R.id.tv_city_name);
        tv_week= (TextView) findViewById(R.id.tv_week);
        tv_moon= (TextView) findViewById(R.id.tv_moon);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    /**
     * 更新ＵＩ
     * @param msg
     */
    @Override
    public void myHandleMessage(Message msg) {
//        super.myHandleMessage(msg);
        switch (msg.what){
            case 0:
                rl_real.setBackgroundResource(R.color.weatherBackground);

                WeatherBean realTime = (WeatherBean) msg.obj;
                tv_direct.setText(realTime.direct);
                tv_power.setText(realTime.power);
                tv_time.setText(realTime.time);
                tv_humidity.setText(realTime.humidity);
                tv_weather.setText(realTime.weather);
                tv_temperature.setText(realTime.temperature);
                tv_date.setText(realTime.date);
                tv_city_name.setText(realTime.city_name);
                tv_week.setText(realTime.week);
                tv_moon.setText(realTime.moon);
                imageView.setImageResource(ImageUtil.getImageDay(realTime.id));
                break;
            case 1:
                List<WeatherInfoBean> data = (List<WeatherInfoBean>) msg.obj;
                adapter =new WeatherAdapter(getApplicationContext(),data);
                lv_weather.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onResume() {
        //new 一个线程进网络访问
        new Thread(){
            @Override
            public void run() {
                super.run();
                weatherUtil =new WeatherUtil("济南");
                WeatherBean realTime =weatherUtil.getRealTime();
                Message message =new Message();
                message.what =0;
                message.obj =realTime;
                handler.sendMessage(message);

                Message message1 =new Message();
                List<WeatherInfoBean> data =weatherUtil.getJsonWeather();
                message1.what=1;
                message1.obj =data;
                handler.sendMessage(message1);
            }
        }.start();
        super.onResume();
    }

    @Override
    public void initEvent() {
        super.initEvent();

        rl_real.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherActivity.this,
                        RealTimeWeatherActivity.class));
            }
        });

        lv_weather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(WeatherActivity.this,
                        WeatherWeekActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }
}
