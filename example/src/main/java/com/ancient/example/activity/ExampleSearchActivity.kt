package com.ancient.example.activity

import android.widget.Toast
import com.ancient.essentials.view.activity.BaseSearchActivity

/**
 * Created by ancientinc on 23/04/20.
 **/
class ExampleSearchActivity : BaseSearchActivity() {

    override fun searchEntered(aSearchValue: String) {
        Toast.makeText(this, "$aSearchValue entered", Toast.LENGTH_SHORT).show()
    }

    override fun getSearchHintText(): String {
        return "Example Search"
    }
}