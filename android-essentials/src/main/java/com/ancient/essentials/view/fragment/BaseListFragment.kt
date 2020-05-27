package com.ancient.essentials.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ancient.essentials.R
import com.ancient.essentials.databinding.ContentListBinding
import com.ancient.essentials.extentions.autoCleared
import com.ancient.essentials.view.widget.LoaderWidget

/**
 * Created by ancientinc on 23/04/20.
 **/
abstract class BaseListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    protected var dataBinding: ContentListBinding by autoCleared()

    protected var showGridLayoutForTab: Boolean = false
    protected var gridSize: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = ContentListBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()

        createRequest(true)
    }

    abstract fun createRequest(isStart: Boolean = false)

    private fun initializeView() {

        val lGridLayoutManager = if (showGridLayoutForTab) {
            GridLayoutManager(context, gridSize)
        } else {
            LinearLayoutManager(context)
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