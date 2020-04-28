package com.ancient.example.activity

import android.os.Bundle
import com.ancient.essentials.example.R
import com.ancient.essentials.view.activity.BaseToolbarActivity

/**
 * Created by ancientinc on 23/04/20.
 */
class ExampleToolbarActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.example_toolbar_page)

        initializeToolbarWithBackNavigation()

        setLightBackNavigationIcon()
    }
}