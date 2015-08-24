package com.noiztezk.pr13.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.noiztezk.pr13.model.Dzkr;
import com.noiztezk.pr13.view.DzkirDetailCounterFragment;

/**
 * Created by Normansyah Putra on 7/26/2015.
 * create fragment using this
 * @deprecated it create more complex using this
 */
public class FragmentFactory {
    private static FragmentFactory mInstance;
    private static final int FFSTATUS_ALIVE = 1, FFSTATUS_DESTROY = 0;
    private static int FragmentFactoryStatus = FFSTATUS_DESTROY;

    public static FragmentFactory getInstance() {
        if(FragmentFactoryStatus == FFSTATUS_ALIVE){
            throw new RuntimeException(FragmentFactory.class.getSimpleName()+"initiate instance allow is once");
        }
        if(mInstance == null && FragmentFactoryStatus == FFSTATUS_DESTROY){
            mInstance = new FragmentFactory();
            FragmentFactoryStatus = FFSTATUS_ALIVE;
        }
        return mInstance;
    }

    public static void destroyIstance(){
        if(FragmentFactoryStatus == FFSTATUS_DESTROY){
            throw new RuntimeException(FragmentFactory.class.getSimpleName()+" object aren't create");
        }
        mInstance = null;
    }

    public Fragment newInstance(Class className, AppCompatActivity activity, Object[] data){
        if(className == DzkirDetailCounterFragment.class){
            return DzkirDetailCounterFragment.newInstance(DzkirDetailCounterFragment.class, (Dzkr)data[0]);
        }
        return null;
    }
}
