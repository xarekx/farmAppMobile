package com.example.farmapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    List<String> listItem;
    Context mContext;


    public GridViewAdapter(List<String> listItem, Context mContext) {
        this.listItem = listItem;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Button button;
        if(convertView == null) {
            button = new Button(mContext);
            button.setLayoutParams(new GridView.LayoutParams(250,250));
            button.setPadding(8,8,8,8);
            button.setText(listItem.get(position));
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.YELLOW);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, button.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            button = (Button)convertView;
        }
        return button;
    }
}
