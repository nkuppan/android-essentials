package com.ancient.essentials.view.activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ancient.essentials.R
import com.ancient.essentials.utils.LocaleHelper

/**
 * Created by ancientinc on 21/04/20.
 *
 * essential 1.0.0 API
 **/
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!resources.getBoolean(R.bool.is_tablet)) {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    /**
     * Attaching fragment in the specified container to show fragment
     *
     * @param aPlaceHolderId will keep the container layout to show fragment
     * @param aFragment to be fetched in the frame of the view
     */
    fun attachThisFragment(aPlaceHolderId: Int, aFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(aPlaceHolderId, aFragment)
            .commitNow()
    }

    /**
     * Setting locale changes to the base context and activity configuration as well
     */
    override fun attachBaseContext(newBase: Context?) {
        var newContext = newBase
        if (newBase != null) {
            newContext = LocaleHelper.onAttach(newBase)
        }

        super.attachBaseContext(newContext)

        applyOverrideConfiguration(newContext?.resources?.configuration)
    }
}