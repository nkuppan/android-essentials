package com.ancient.essentials.view.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * Created by ancientinc on 2020-04-22.
 **/
class BaseFragmentPagerAdapter(
        fragmentManager: FragmentManager,
        private var mFragments: MutableList<Fragment>,
        private var mFragmentsTitle: MutableList<String>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (mFragmentsTitle.isNotEmpty())
            mFragmentsTitle[position]
        else
            ""
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    fun setAdapterValue(aFragments: MutableList<Fragment>,
                        aFragmentsTitle: MutableList<String>) {
        this.mFragments = aFragments
        this.mFragmentsTitle = aFragmentsTitle
    }

    //this is called when notifyDataSetChanged() is called
    override fun getItemPosition(`object`: Any): Int {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE
    }
}