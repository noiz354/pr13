package com.noiztezk.pr13;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by noiz354 on 4/27/16.
 */
public class PRThirteenApplication extends Application {
    private final NetComponent mNetComponent = createComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        onCreateReal();
    }

    protected void onCreateReal(){
        JodaTimeAndroid.init(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

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
