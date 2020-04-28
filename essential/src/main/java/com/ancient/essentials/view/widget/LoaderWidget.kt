package com.ancient.essentials.view.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.StringRes
import com.ancient.essentials.R

class LoaderWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    RelativeLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var mLoadingView: ProgressBar? = null

    private var mFailureTitleView: TextView? = null

    private var mFailureImageView: ImageView? = null

    private var mRetryView: Button? = null

    /**
     * @return retry button listener for retry button
     */
    var onRetryListener: OnRetryListener? = null

    private var mAttachedView: View? = null

    private var mNoResultFoundText: String? = null

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        LayoutInflater.from(context).inflate(R.layout.widget_loader, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        initializeViews()
    }

    private fun initializeViews() {

        mLoadingView = findViewById(R.id.loading_view)
        mFailureImageView = findViewById(R.id.failure_image)
        mFailureTitleView = findViewById(R.id.failure_text)
        mRetryView = findViewById(R.id.retry_button)

        setLoadingWidgetState(HIDE_EVERYTHING)

        mRetryView!!.setOnClickListener(this)
    }

    /**
     * Modifying widget states as per the state
     *
     * @param aWidgetState widget will be modified as per the state param
     */
    private fun setLoadingWidgetState(aWidgetState: Int) {

        if (aWidgetState == SHOW_RESULT) {
            visibility = View.GONE
            setVisibilityForAttachedView(View.VISIBLE)
            setFailureText("")
            return
        }

        if (aWidgetState == HIDE_EVERYTHING) {
            visibility = View.GONE
            setVisibilityForAttachedView(View.GONE)
            return
        } else {
            setVisibilityForAttachedView(View.GONE)
            visibility = View.VISIBLE
        }

        if (aWidgetState == LOADING_STATE) {

            mFailureImageView!!.visibility = View.GONE
            mFailureTitleView!!.visibility = View.GONE
            mRetryView!!.visibility = View.GONE

            mLoadingView!!.visibility = View.VISIBLE
        } else {
            mLoadingView!!.visibility = View.GONE
        }


        if (aWidgetState == FAILURE_STATE) {

            mFailureImageView!!.visibility = View.VISIBLE
            mFailureTitleView!!.visibility = View.VISIBLE
            mRetryView!!.visibility = View.VISIBLE

        } else if (aWidgetState == NO_RESULT_STATE) {

            mFailureImageView!!.visibility = View.GONE
            mFailureTitleView!!.visibility = View.VISIBLE
            mRetryView!!.visibility = View.GONE
        }
    }

    /**
     * @param aVisibility visibility for attached view
     */
    private fun setVisibilityForAttachedView(aVisibility: Int) {
        if (mAttachedView != null) {
            mAttachedView!!.visibility = aVisibility
        }
    }

    /**
     * Internet failure message showing
     */
    fun setInternetFailureView() {

        setLoadingWidgetState(FAILURE_STATE)

        setFailureText(resources.getString(R.string.internet_failure_message))
    }

    /**
     * Service failure message showing
     */
    fun setServiceFailureView() {

        setLoadingWidgetState(FAILURE_STATE)

        setFailureText(resources.getString(R.string.service_failure_message))
    }

    /**
     * setting text value
     *
     * @param aFailureText failure text value
     */
    private fun setFailureText(aFailureText: String?) {
        mFailureTitleView!!.text = if (TextUtils.isEmpty(aFailureText)) "" else aFailureText
        mFailureTitleView!!.visibility = View.VISIBLE
    }

    /**
     * setting text value
     *
     * @param aFailureText failure text value
     */
    fun setFailureText(@StringRes aFailureText: Int) {
        mFailureTitleView!!.setText(aFailureText)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.retry_button) {
            onRetryListener?.onRetry()
        }
    }

    /**
     * Loading started for this view
     */
    fun startLoading() {
        setLoadingWidgetState(LOADING_STATE)
    }

    /**
     * No result found view
     */
    fun setNoResultFoundView() {

        setLoadingWidgetState(NO_RESULT_STATE)

        setFailureText(
            if (TextUtils.isEmpty(mNoResultFoundText))
                resources.getString(R.string.no_data_available)
            else
                mNoResultFoundText
        )
    }

    /**
     * @param aAttachedView attached view
     */
    fun attachListView(aAttachedView: View) {
        this.mAttachedView = aAttachedView
    }

    /**
     * Call this method when you got the response
     */
    fun setResultFoundView() {
        setLoadingWidgetState(SHOW_RESULT)
    }

    /**
     * @param aNoResultFoundText text to be displayed in no result view
     */
    fun setNoResultFoundText(aNoResultFoundText: String) {
        this.mNoResultFoundText = aNoResultFoundText
    }

    /**
     * OnRetry listener for loading widget view
     */
    interface OnRetryListener {
        fun onRetry()
    }

    companion object {

        const val HIDE_EVERYTHING = 0x0001

        const val LOADING_STATE = HIDE_EVERYTHING shl 1

        const val NO_RESULT_STATE = LOADING_STATE shl 1

        const val FAILURE_STATE = NO_RESULT_STATE shl 1

        const val SHOW_RESULT = FAILURE_STATE shl 1
    }
}
