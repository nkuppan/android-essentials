package com.ancient.essentials.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ancient.essentials.R
import com.ancient.essentials.databinding.TabsPageLayoutBinding
import com.ancient.essentials.extentions.EventObserver
import com.ancient.essentials.extentions.autoCleared
import com.ancient.essentials.extentions.obtainBaseViewModel
import com.ancient.essentials.view.adapter.BaseFragmentPagerAdapter
import com.ancient.essentials.view.viewmodel.TabsViewModel
import com.google.android.material.tabs.TabLayout

/**
 * Created by ancientinc on 2020-04-22.
 **/
abstract class BaseTabActivity : BaseToolbarActivity() {

    private var viewModel: TabsViewModel by autoCleared()

    private var dataBinding: TabsPageLayoutBinding by autoCleared()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.tabs_page_layout)

        viewModel = obtainBaseViewModel(TabsViewModel::class.java)

        dataBinding.viewModel = viewModel

        dataBinding.lifecycleOwner = this

        initializeToolbar()

        title = getPageTitle()

        viewModel.tabsAdapter.observe(this, EventObserver {
            if (it != null) {
                dataBinding.pager.adapter = it
            }
        })

        dataBinding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                //Do nothing
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //Do nothing
            }

            override fun onPageSelected(position: Int) {
                viewModel.selectedTabPosition = position
            }
        })
    }

    /**
     * While started to loading a page can use this loader to until the page ends to complete the
     * task scheduled in the fragment
     *
     * @param isLoading will keep the status of the loader
     */
    fun setLoading(isLoading: Boolean) {
        viewModel.isLoading.value = isLoading
    }

    /**
     * @param aMode will change the tablayout mode
     */
    fun setTabMode(@TabLayout.Mode aMode: Int) {
        dataBinding.tabLayout.tabMode = aMode
    }

    /**
     * Can set the list of fragments and fragment titles to be set in the tabs
     * @see TabLayout is used to wrap the tab bars as per material design
     * @see ViewPager will keep fragment pages and
     *
     * @param aPageTitles will keep the list of fragment pages titles to be shown in tabs layout
     * @param aPageFragments will keep list of fragment pages to shown in the view pager
     */
    fun setAdapter(aPageTitles: List<String>, aPageFragments: List<Fragment>) {

        setLoading(isLoading = true)

        if (dataBinding.pager.adapter == null) {

            viewModel.setAdapter(
                BaseFragmentPagerAdapter(
                    fragmentManager = supportFragmentManager,
                    mFragments = aPageFragments.toMutableList(),
                    mFragmentsTitle = aPageTitles.toMutableList()
                )
            )

            dataBinding.tabLayout.setupWithViewPager(dataBinding.pager)

        } else {
            viewModel.updateAdapter(
                aFragments = aPageFragments.toMutableList(),
                aFragmentsTitle = aPageTitles.toMutableList()
            )
        }

        dataBinding.pager.currentItem = viewModel.selectedTabPosition

        setLoading(isLoading = false)
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.setAdapter(null)
    }

    /**
     * @return the title of the tabs layout page
     */
    abstract fun getPageTitle(): String
}