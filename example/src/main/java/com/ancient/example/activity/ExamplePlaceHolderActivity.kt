package com.ancient.example.activity

import android.os.Bundle
import com.ancient.essentials.example.R
import com.ancient.essentials.view.activity.BasePlaceHolderActivity
import com.ancient.essentials.view.activity.BaseToolbarActivity
import com.ancient.example.fragment.ExampleFragment

/**
 * Created by ancientinc on 23/04/20.
 */
class ExamplePlaceHolderActivity : BasePlaceHolderActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachThisFragment(ExampleFragment())
    }
}