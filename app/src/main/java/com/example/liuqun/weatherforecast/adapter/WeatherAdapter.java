package com.example.liuqun.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.liuqun.weatherforecast.Bean.WeatherInfoBean;
import com.example.liuqun.weatherforecast.R;

import java.util.List;

/**
 * Created by 90516 on 5/27/2016.
 */
public class WeatherAdapter extends BaseAdapter {

    LayoutInflater inflater;

    List<WeatherInfoBean> data;

    public WeatherAdapter(Context context,List<WeatherInfoBean> data) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public WeatherInfoBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder =new ViewHolder();
            convertView =inflater.inflate(R.layout.layout_list_weather,null);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.weather = (TextView) convertView.findViewById(R.id
                    .tv_weather);
            holder.temperature = (TextView) convertView.findViewById(R.id.tv_temperature);
            holder.direct = (TextView) convertView.findViewById(R.id.tv_direct);
            holder.power = (TextView) convertView.findViewById(R.id.tv_power);
            holder.week = (TextView) convertView.findViewById(R.id.tv_week);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.date.setText(getItem(position).date);
        holder.weather.setText(getItem(position).weather_day);
        holder.temperature.setText("温度"+getItem(position)
                .temperature_night+"~"+getItem(position).temperature_day+"℃");
        holder.direct.setText(getItem(position).direct_day);
        holder.power.setText(getItem(position).power_day);
        holder.week.setText("星期"+getItem(position).week);

        return convertView;
    }

    private class ViewHolder {
        TextView date;
        TextView weather;
        TextView temperature;
        TextView direct;
        TextView power;
        TextView week;
    }
}
