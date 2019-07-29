package com.example.farmapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmapp.LootActivity;
import com.example.farmapp.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<String> mobNameList;
    private Context context;
    private int[] imageId;
    private static LayoutInflater layoutInflater= null;

    public CustomAdapter(ArrayList<String> mobNameList, Context context, int[] imageId) {
        this.mobNameList = mobNameList;
        this.context = context;
        this.imageId = imageId;

    }

    @Override
    public int getCount() {
        return mobNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return mobNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        View rowView;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView =  layoutInflater.inflate(R.layout.list_item,parent,false);
        holder.tv = rowView.findViewById(R.id.mob_name);
        holder.img = rowView.findViewById(R.id.mob_image);
        holder.tv.setText(mobNameList.get(position));
        holder.img.setImageResource(imageId[position]);
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, ""+mobNameList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
        return rowView;
    }


    public class Holder {
        TextView tv;
        ImageView img;

    }


}
