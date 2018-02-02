package com.example.prateek.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by PRATEEK on 29-Jan-18.
 */

public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {


    private String[] arr = {"login","register"};

    public SimpleFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new Frag1();
        else
            return new Frag2();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arr[position];
    }
}
