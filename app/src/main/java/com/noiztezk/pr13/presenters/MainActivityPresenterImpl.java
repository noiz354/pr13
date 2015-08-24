package com.noiztezk.pr13.presenters;

import com.noiztezk.pr13.interfaces.MainActivityView;

/**
 * Created by SRIN on 7/30/2015.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    MainActivityView mainActivityView;

    public MainActivityPresenterImpl(MainActivityView mainActivityView){
        this.mainActivityView = mainActivityView;
    }

    @Override
    public void setActionBarTitle(CharSequence text) {
        mainActivityView.setActionBarTitle(text);
    }
}
