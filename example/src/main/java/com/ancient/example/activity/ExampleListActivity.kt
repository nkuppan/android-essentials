package com.ancient.example.activity

import com.ancient.essentials.view.activity.BaseListActivity
import com.ancient.example.adapter.SimpleAdapter

/**
 * Created by ancientinc on 23/04/20.
 **/
class ExampleListActivity : BaseListActivity() {

    private val listItems = mutableListOf<String>()

    override fun createRequest(isStart: Boolean) {
        if (isStart) {

            repeat(10) {
                listItems.add("Index $it")
            }

            val simpleAdapter = SimpleAdapter(listItems)
            setAdapter(simpleAdapter)
        }
    }

    override fun getPageTitle(): String {
        return "Example List Page"
    }
}