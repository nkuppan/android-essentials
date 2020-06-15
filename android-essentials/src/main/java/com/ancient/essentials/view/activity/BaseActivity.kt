package com.ancient.essentials.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ancient.essentials.utils.LocaleHelper

/**
 * Created by ancientinc on 21/04/20.
 *
 * essential 1.0.0 API
 **/
open class BaseActivity : AppCompatActivity() {

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

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { LocaleHelper.onAttach(it) })
    }
}