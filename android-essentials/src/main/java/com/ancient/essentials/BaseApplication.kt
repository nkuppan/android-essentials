package com.ancient.essentials

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.ancient.essentials.utils.LocaleHelper.onAttach

/**
 * Created by ancientinc on 20/04/20.
 **/
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        var newContext = base
        if (base != null) {
            newContext = onAttach(base)
        }
        super.attachBaseContext(newContext)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        onAttach(this)
    }
}