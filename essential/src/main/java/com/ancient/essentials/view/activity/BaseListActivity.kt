package com.ancient.essentials.view.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ancient.essentials.R
import com.ancient.essentials.databinding.ListPageBinding
import com.ancient.essentials.extentions.autoCleared
import com.ancient.essentials.view.widget.LoaderWidget

/**
 * Created by ancientinc on 23/04/20.
 *
 * essential 1.0.0 API
 **/
abstract class BaseListActivity : BaseToolbarActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var dataBinding: ListPageBinding by autoCleared()

    protected var showGridLayoutForTab: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.list_page)

        dataBinding.lifecycleOwner = this

        initializeToolbar()

        title = getPageTitle()

        initializeView()

        createRequest(true)
    }

    /**
     * Refresh callback event to the proceed data load or reload process to the child classes
     */
    abstract fun createRequest(isStart: Boolean = false)

    /**
     * @return the title of the tabs layout page
     */
    abstract fun getPageTitle(): String

    private fun initializeView() {

        val lGridLayoutManager =
            if (resources.getBoolean(R.bool.is_tablet) && showGridLayoutForTab) {
                GridLayoutManager(this, resources.getInteger(R.integer.span_count))
            } else {
                GridLayoutManager(this, 1)
            }

        dataBinding.recyclerview.layoutManager = lGridLayoutManager
        dataBinding.recyclerview.itemAnimator = DefaultItemAnimator()
        dataBinding.recyclerview.setHasFixedSize(true)

        dataBinding.loaderWidget.attachListView(dataBinding.recyclerview)
        dataBinding.loaderWidget.setNoResultFoundText(getString(R.string.no_data_available))

        dataBinding.refreshLayout.setOnRefreshListener(this)

        dataBinding.loaderWidget.onRetryListener = object : LoaderWidget.OnRetryListener {
            override fun onRetry() {
                createRequest()
            }
        }
    }

    fun setHasFixedSize(aFixedSize: Boolean) {
        dataBinding.recyclerview.setHasFixedSize(aFixedSize)
    }

    protected fun setActionIconDrawable(@DrawableRes aDrawable: Int) {
        dataBinding.actionButton.setImageResource(aDrawable)
    }

    protected fun setActionClickListener(aClickListener: View.OnClickListener) {
        showActionButton()
        dataBinding.actionButton.setOnClickListener(aClickListener)
    }

    protected fun showActionButton() {
        dataBinding.actionButton.visibility = View.VISIBLE
    }

    protected fun hideActionButton() {
        dataBinding.actionButton.visibility = View.GONE
    }

    protected fun showFailureView(isInternetFailure: Boolean = false) {

        dataBinding.refreshLayout.isRefreshing = false

        if (isInternetFailure) {
            dataBinding.loaderWidget.setInternetFailureView()
        } else {
            dataBinding.loaderWidget.setServiceFailureView()
        }
    }

    protected fun startLoadingRequest() {
        dataBinding.refreshLayout.isRefreshing = true
        dataBinding.loaderWidget.startLoading()
    }

    protected fun setNoResult() {
        dataBinding.refreshLayout.isRefreshing = false
        dataBinding.loaderWidget.setNoResultFoundView()
    }

    protected fun setResult() {
        dataBinding.refreshLayout.isRefreshing = false
        dataBinding.loaderWidget.setResultFoundView()
    }

    protected fun setAdapter(aAdapter: RecyclerView.Adapter<*>) {
        dataBinding.recyclerview.adapter = aAdapter
    }

    override fun onRefresh() {
        createRequest()
    }
}