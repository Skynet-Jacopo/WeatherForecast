package com.example.liuqun.weatherforecast.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.liuqun.weatherforecast.utils.WeatherUtil;

/**
 * Created by 90516 on 5/27/2016.
 */
public class MyActivity extends AppCompatActivity {

    public static WeatherUtil weatherUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    public void initView() {

    }

    public void initData() {

    }

    public void initEvent() {

    }
    public Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            myHandleMessage(msg);
        }
    };

    public void myHandleMessage(Message msg) {

    }
    public void startActvity(Class activity){
        Intent intent =new Intent(this,activity);
        startActivity(intent);
        finish();
    }
}
