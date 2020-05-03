package com.ancient.essentials.extentions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by ancientinc on 22/04/20.
 **/

fun <T : ViewModel> Fragment.obtainBaseViewModel(viewModelClass: Class<T>): T {
    return ViewModelProvider(this).get(viewModelClass)
}

fun <T : ViewModel> FragmentActivity.obtainBaseViewModel(viewModelClass: Class<T>): T {
    return ViewModelProvider(this).get(viewModelClass)
}