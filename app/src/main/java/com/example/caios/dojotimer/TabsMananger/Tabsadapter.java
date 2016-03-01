package com.example.caios.dojotimer.TabsMananger;

/**
 * Created by caios on 2/21/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.caios.dojotimer.TabsMananger.Tab.HistoryFragment;
import com.example.caios.dojotimer.TabsMananger.Tab.RoundFragment;

public class Tabsadapter extends FragmentStatePagerAdapter {

    private int TOTAL_TABS = 2;

    public Tabsadapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        switch (index) {
            case 0:
                return new RoundFragment();

            case 1:
                return new HistoryFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}