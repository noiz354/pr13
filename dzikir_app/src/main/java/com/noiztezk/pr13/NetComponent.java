package com.noiztezk.pr13;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by noiz354 on 4/27/16.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Application provideApplication();
    void inject(HomeActivity homeActivity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}
