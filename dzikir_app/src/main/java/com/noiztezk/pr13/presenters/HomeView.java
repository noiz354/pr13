package com.noiztezk.pr13.presenters;

/**
 * Created by noiz354 on 7/28/16.
 */

public interface HomeView {
    String FIRST_TIME = "first_time";
    String DEF_PR13_JSON = "def_pr13.json";

    String[] getKnownAudioFormat();
    String getSalamText();
    boolean isFirstTime();

    /**
     *
     * @param firstTimeText
     * @param firstTime true if want to reset to first time, false if not first Time
     */
    void setFirstTime(String firstTimeText, boolean firstTime);
}
