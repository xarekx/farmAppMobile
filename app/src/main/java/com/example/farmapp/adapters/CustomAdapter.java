package com.example.farmapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmapp.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<String> mobNameList;
    Context context;
    int[] imageId;
    private static LayoutInflater layoutInflater= null;

    public CustomAdapter(ArrayList<String> mobNameList, Context context, int[] imageId) {
        this.mobNameList = mobNameList;
        this.context = context;
        this.imageId = imageId;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        Holder holder = new Holder();
        View rowView;
        rowView = layoutInflater.inflate(R.layout.list_item,null);
        holder.tv = rowView.findViewById(R.id.mob_name);
        holder.img = rowView.findViewById(R.id.mob_image);
        holder.tv.setText(mobNameList.get(position));
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked"+mobNameList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }
    public class Holder {
        TextView tv;
        ImageView img;
    }


}
