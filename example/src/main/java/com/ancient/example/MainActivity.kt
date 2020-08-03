package com.ancient.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ancient.essentials.example.R
import com.ancient.essentials.utils.SecuredPreferenceManager
import com.ancient.essentials.view.activity.BaseActivity
import com.ancient.example.activity.ExampleListActivity
import com.ancient.example.activity.ExamplePlaceHolderActivity
import com.ancient.example.activity.ExampleSearchActivity
import com.ancient.example.activity.ExampleToolbarActivity

/**
 * Created by ancientinc on 22/04/20.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val pref = SecuredPreferenceManager.initialize(this, "sample")
    }

    fun toolbarActivityClick(view: View) {
        startActivity(Intent(this, ExampleToolbarActivity::class.java))
    }

    fun searchActivityClick(view: View) {
        startActivity(Intent(this, ExampleSearchActivity::class.java))
    }

    fun placeHolderClick(view: View) {
        startActivity(Intent(this, ExamplePlaceHolderActivity::class.java))
    }

    fun listActivityClick(view: View) {
        startActivity(Intent(this, ExampleListActivity::class.java))
    }

    fun listFragmentClick(view: View) {
        startActivity(Intent(this, ExampleToolbarActivity::class.java))
    }
}