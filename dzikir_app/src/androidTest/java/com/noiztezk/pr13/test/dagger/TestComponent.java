package com.noiztezk.pr13.test.dagger;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.noiztezk.pr13.NetComponent;
import com.noiztezk.pr13.test.db.AudioTypeTest;
import com.noiztezk.pr13.test.db.DzikirTest;
import com.noiztezk.pr13.test.ui.HomeActivityTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockAppModule.class, MockNetModule.class})
public interface TestComponent extends NetComponent {
    SharedPreferences sharedPreferences();
    Gson gson();
    void inject(AudioTypeTest audioTypeTest);

    void inject(HomeActivityTest homeActivityTest);
    void inject(DzikirTest dzikirTest);
}