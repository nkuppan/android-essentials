package com.ancient.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ancient.essentials.example.R
import com.ancient.essentials.view.fragment.BaseFragment

/**
 * Created by ancientinc on 23/04/20.
 **/
class ExampleFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.example_content, container, false)
    }
}