package com.example.farmapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farmapp.R;

import java.util.ArrayList;

public class CustomItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> objects;
    private ArrayList<Integer> resource;

    public CustomItemAdapter(Context context, ArrayList<Integer> resource,  ArrayList<String> objects) {
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    private class ViewHolder {
        ImageView iv;
        TextView tv;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null) {
            convertView = layoutInflater.inflate(R.layout.loot_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv = convertView.findViewById(R.id.loot_item_photo);
            viewHolder.tv = convertView.findViewById(R.id.loot_item_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.iv.setImageResource(resource.get(position));
            viewHolder.tv.setText(objects.get(position));

        return convertView;
    }
}
