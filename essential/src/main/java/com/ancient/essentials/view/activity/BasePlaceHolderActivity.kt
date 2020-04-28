package com.ancient.essentials.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ancient.essentials.R

/**
 * This activity will be used to reduced the activity which is need to attach a single fragment
 * or toolbar with
 * essential 1.0.0 API
 */
open class BasePlaceHolderActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.place_holder_page)

        initializeToolbar()
    }

    /**
     * Attaching fragment into the specified frame layout and showing that value
     *
     * @param aFragment this will be attached to the frame
     */
    protected fun attachThisFragment(aFragment: Fragment) {
        attachThisFragment(R.id.place_holder, aFragment)
    }
}