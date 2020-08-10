package com.ancient.essentials.view.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ancient.essentials.R
import com.ancient.essentials.databinding.SearchPageBinding
import com.ancient.essentials.extentions.autoCleared
import com.ancient.essentials.extentions.obtainBaseViewModel
import com.ancient.essentials.view.viewmodel.SearchViewModel
import java.util.*


/**
 * This activity will keep the toolbar and search view inside to it. It will also contain google
 * voice search option. You will have a abstract method and it will be whenever the user press done
 * button from the search bar and method will be notified along with the search text.
 *
 * @see BaseToolbarActivity  is used to keep the toolbar initialization
 *
 * Created by ancientinc on 22/04/20.
 **/
abstract class BaseSearchActivity : BaseToolbarActivity(), TextView.OnEditorActionListener {

    private var viewModel: SearchViewModel by autoCleared()

    private var dataBinding: SearchPageBinding by autoCleared()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.search_page)

        viewModel = obtainBaseViewModel(SearchViewModel::class.java)

        dataBinding.lifecycleOwner = this

        dataBinding.viewModel = viewModel

        dataBinding.searchView.setOnEditorActionListener(this)

        viewModel.searchText.observe(this, Observer {
            if (enableEachLetterSearchAction()) {
                processValue(it)
            }
        })

        viewModel.backNavigation.observe(this, Observer {
            onBackPressed()
        })

        viewModel.voiceSearch.observe(this, Observer {
            promptSpeechInput()
        })

        viewModel.searchHintText.value = getSearchHintText()
    }

    private fun processValue(it: String) {
        if (it.isBlank() && it.length <= 2) {
            return
        }

        hideKeyboard(dataBinding.searchView)
        searchEntered(it)
    }

    /**
     * Showing google speech input dialog
     */
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            //Do nothing
        }
    }

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {

                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )

                    if (result.isNullOrEmpty()) {
                        return
                    }

                    viewModel.searchText.value = result[0]
                }
            }
        }
    }

    private fun hideKeyboard(aView: View?) {
        aView?.let {
            val lInputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            lInputMethodManager.hideSoftInputFromWindow(aView.windowToken, 0)
        }
    }

    protected fun setLightSearchBar() {
        dataBinding.searchView.setTextColor(ContextCompat.getColor(this, R.color.black))
        dataBinding.searchView.setHintTextColor(ContextCompat.getColor(this, R.color.black))

        dataBinding.backButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_arrow_back_black
            )
        )
        dataBinding.clearButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_close
            )
        )
        dataBinding.voiceSearchButton.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_voice_search
            )
        )
    }

    /**
     * Attaching the fragment into the place holder
     *
     * @param aFragment will fetch the placeholder list fragment
     */
    protected fun attachThisFragment(aFragment: Fragment) {
        attachThisFragment(R.id.place_holder, aFragment)
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            processValue(viewModel.searchText.value ?: "")
            return true
        }
        return false
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
    }

    abstract fun searchEntered(aSearchValue: String)

    abstract fun getSearchHintText(): String

    fun enableEachLetterSearchAction(): Boolean {
        return true
    }
}