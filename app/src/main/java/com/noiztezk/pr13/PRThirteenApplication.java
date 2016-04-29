package com.noiztezk.pr13;

import android.app.Application;

/**
 * Created by noiz354 on 4/27/16.
 */
public class PRThirteenApplication extends Application {
    private final NetComponent mNetComponent = createComponent();

    protected NetComponent createComponent() {
        // Dagger%COMPONENT_NAME%
        // list of modules that are part of this component need to be created here too
        return DaggerNetComponent.builder()
                // This also corresponds to the name of your module: %component_name%Module
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }
}
