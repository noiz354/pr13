package com.noiztezk.pr13

import android.app.Application
import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by normansyahputa on 9/29/17.
 */
open class App : Application(){
    var mNetComponent = createComponent()

    protected open fun createComponent(): NetComponent  = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()

    override fun onCreate() {
        super.onCreate()
        onCreateReal()
    }

    protected open fun onCreateReal() {
        JodaTimeAndroid.init(this)
        FlowManager.init(FlowConfig.Builder(this).build())
        Stetho.initializeWithDefaults(this)
    }
}