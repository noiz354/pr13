package com.noiztezk.pr13.presenters;

/**
 * Created by noiz354 on 7/28/16.
 */

public class HomeImpl implements Home {
    HomeView homeView;
    private HomeImpl(){}
    public HomeImpl(HomeView homeView){this.homeView = homeView;}
    @Override
    public void insertKnownAudioType(String[] audioFormat) {

    }
}
