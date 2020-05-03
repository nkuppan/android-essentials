package com.ancient.essentials.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ancient.essentials.extentions.Event

/**
 * Created by ancientinc on 22/04/20.
 **/

class SearchViewModel(private val aApplication: Application) : AndroidViewModel(aApplication) {

    var searchText = MutableLiveData<String>()

    var searchHintText = MutableLiveData<String>()

    val backNavigation = MutableLiveData<Event<Unit>>()

    val voiceSearch = MutableLiveData<Event<Unit>>()

    init {
        searchText.value = ""
    }

    fun voiceSearch() {
        voiceSearch.value = Event(Unit)
    }

    fun setSearchHintText() {
        voiceSearch.value = Event(Unit)
    }

    fun clearSearch() {
        searchText.value = ""
    }

    fun backNavigation() {
        backNavigation.value = Event(Unit)
    }
}