package com.example.liuqun.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liuqun.weatherforecast.Bean.LifeBean;
import com.example.liuqun.weatherforecast.R;

import java.util.List;

/**
 * Created by 90516 on 5/27/2016.
 */
public class LifeAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<LifeBean> data;

    public LifeAdapter(Context context, List<LifeBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public LifeBean getItem(int position) {
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
            convertView =inflater.inflate(R.layout.layout_life_weather,null);
            holder.title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.exp = (TextView) convertView.findViewById(R.id.tv_exp );
            holder.miute = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).title);
        holder.exp.setText(getItem(position).exp);
        holder.miute.setText(getItem(position).content);
        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView exp;
        TextView miute;
    }
}
