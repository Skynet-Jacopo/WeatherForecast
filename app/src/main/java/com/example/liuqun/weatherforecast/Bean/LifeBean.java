package com.example.liuqun.weatherforecast.Bean;

/**
 * Created by 90516 on 5/27/2016.
 */
public class LifeBean {
    public String title;//生活指数类型
    public String exp;//指数
    public String content;//详细介绍

    public LifeBean(String exp, String content) {
        this.exp = exp;
        this.content = content;
    }
}
