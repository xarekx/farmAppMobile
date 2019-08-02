package com.example.farmapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.farmapp.FirstItemTab;
import com.example.farmapp.LootActivity;
import com.example.farmapp.SecondItemTab;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int countTab;

    public PageAdapter(FragmentManager fm,int countTab) {
        super(fm);
        this.countTab = countTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                return new FirstItemTab();
            case 1:
                return new SecondItemTab();
                default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
