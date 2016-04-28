package com.noiztezk.pr13.utils;

import com.noiztezk.pr13.DzkirDetailActivity;
import com.noiztezk.pr13.view.DzikirAdapter2;
import com.noiztezk.pr13.view.DzkirDetailCounterFragment;

/**
 * Created by SRIN on 7/27/2015.
 */
public class Constants {
    public static final String customObject[] = {
            DzikirAdapter2.class.getSimpleName()+" to "+DzkirDetailActivity.class.getSimpleName(),// 0
            DzkirDetailActivity.class.getSimpleName()+" to "+DzkirDetailCounterFragment.class.getSimpleName(),// 1
            DzkirDetailCounterFragment.class.getSimpleName()+" to "+DzkirDetailActivity.class.getSimpleName(), // 2
            DzkirDetailCounterFragment.class.getSimpleName()+" to "+DzkirDetailActivity.class.getSimpleName()+" Array", // 3
            DzkirDetailCounterFragment.class.getSimpleName()+" to "+DzkirDetailActivity.class.getSimpleName()+" Array Index", // 4
            DzkirDetailActivity.class.getSimpleName()+" to "+DzkirDetailCounterFragment.class.getSimpleName()+" Array", // 5
    };

    public static final String customFragmentTag[] = {
      DzkirDetailCounterFragment.class.getSimpleName()+"_TAG"
    };

    public static final String customFragmentRotate[] = {
            DzkirDetailCounterFragment.class.getSimpleName()+"_data"
    };

}
