package com.noiztezk.pr13.presenters;

import android.content.Context;

import com.noiztezk.pr13.model.Dzikir;

/**
 * Created by noiz354 on 5/13/16.
 */
public interface DzkirDetailView {
    void setCounterText(String dzkirCount);
    void setArabicText(String arabicText);
    Context getViewContext();
    Dzikir getDzikir();
}
