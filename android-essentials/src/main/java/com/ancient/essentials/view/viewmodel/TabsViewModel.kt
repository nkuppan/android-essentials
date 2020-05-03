package com.ancient.essentials.view.viewmodel

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ancient.essentials.extentions.Event
import com.ancient.essentials.view.adapter.BaseFragmentPagerAdapter

/**
 * Created by ancientinc on 2020-04-22.
 **/
class TabsViewModel(aApplication: Application) : AndroidViewModel(aApplication) {

    val isLoading = MutableLiveData<Boolean>()

    val tabsAdapter = MutableLiveData< Event<BaseFragmentPagerAdapter?>>()

    var selectedTabPosition: Int = 0

    init {
        isLoading.value = true
    }

    fun setAdapter(adapter: BaseFragmentPagerAdapter?) {
        isLoading.value = false
        tabsAdapter.value = Event(adapter)
    }

    fun updateAdapter(aFragments: MutableList<Fragment>, aFragmentsTitle: MutableList<String>) {
        isLoading.value = false
        tabsAdapter.value?.peekContent()?.setAdapterValue(
                aFragments = aFragments,
                aFragmentsTitle = aFragmentsTitle
        )
    }
}
