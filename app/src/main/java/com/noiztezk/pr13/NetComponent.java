package com.noiztezk.pr13;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by noiz354 on 4/27/16.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}
