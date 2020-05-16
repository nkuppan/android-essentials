package com.ancient.essentials.view.activity

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import com.ancient.essentials.R

/**
 * Created by ancientinc on 20/04/20.
 **/
open class BaseToolbarActivity : BaseActivity() {

    private var mToolbar: Toolbar? = null

    /**
     * Method is used to initialize toolbar with activity and handle back navigation
     *
     * @param aNavigationIcon this icon will be replace as a navigation icon of the toolbar
     */
    fun initializeToolbar(aNavigationIcon: Int) {

        mToolbar = findViewById(R.id.toolbar)

        mToolbar?.let { it ->

            it.setNavigationIcon(aNavigationIcon)

            setSupportActionBar(mToolbar)

            supportActionBar?.let { actionBar ->

                actionBar.setDisplayHomeAsUpEnabled(true)

                mToolbar?.setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    /**
     * Initializing toolbar with back default navigation
     */
    protected fun initializeToolbarWithBackNavigation() {
        initializeToolbar(R.drawable.ic_arrow_back)
    }

    /**
     * Just initializing the toolbar and setting that into activity
     */
    fun initializeToolbar() {

        mToolbar = findViewById(R.id.toolbar)

        mToolbar?.let {
            setSupportActionBar(mToolbar)
        }
    }

    /**
     * This method will set the back navigation arrow icon with black color to support the light
     * theme behaviour to the application
     */
    fun setLightBackNavigationIcon() {
        setNavigationIcon(R.drawable.ic_arrow_back_white)
    }

    /**
     * This method will set the back navigation arrow icon with black color to support the dark
     * theme behaviour to the application
     */
    fun setDarkBackNavigationIcon() {
        setNavigationIcon(R.drawable.ic_arrow_back_black)
    }

    /**
     * @param aNavigationIcon this icon will set into the toolbar navigation icon
     */
    fun setNavigationIcon(@DrawableRes aNavigationIcon: Int) {
        mToolbar?.setNavigationIcon(aNavigationIcon)
    }

    /**
     * Hiding toolbar from activity
     */
    fun hideToolbar() {
        mToolbar?.visibility = View.GONE
    }
}