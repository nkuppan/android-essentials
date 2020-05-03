package com.ancient.essentials.view.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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
}