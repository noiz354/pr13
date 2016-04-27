package com.noiztezk.pr13.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.noiztezk.pr13.model.Dzikir;

import java.util.List;

/**
 * Created by SRIN on 7/27/2015.
 */
public class DzkirPagerAdapter extends FragmentPagerAdapter {
    List<Dzikir> DzkrRef;
    public DzkirPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDzkrRef(List<Dzikir> dzkrRef) {
        DzkrRef = dzkrRef;
    }

    @Override
    public Fragment getItem(int position) {
//        if(DzkrRef == null)
//            throw new RuntimeException("call setDzkrRef in attempt to create view pager ");
        return new DzkirDetailExplainFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
