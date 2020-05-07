package com.ancient.essentials.extentions

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ancient.essentials.view.widget.InputEditTextView
import com.google.android.material.snackbar.Snackbar


/**
 * Created by ancientinc on 2020-01-17.
 **/


/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).run {
        show()
    }
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), timeLength)
        }
    })
}

@BindingAdapter(value = ["app:editTextStringAttrChanged"])
fun setEditTextString(editText: InputEditTextView, listener: InverseBindingListener?) {
    if (listener != null) {
        editText.mEditTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Do nothing
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isNotBlank()) {
                    editText.setErrorText(null)
                }
            }

            override fun afterTextChanged(editable: Editable) {
                listener.onChange()
            }
        })
    }
}

@BindingAdapter("app:editTextString")
fun setEditTextString(editText: InputEditTextView, text: String?) {
    text?.let {
        if (it != editText.mEditTextView.text.toString()) {
            editText.mEditTextView.setText(it, false)
        }
    }
}

@BindingAdapter("app:errorText")
fun setHintTextString(editText: InputEditTextView, text: String?) {
    editText.setErrorText(text)
}

@BindingAdapter("app:inputType")
fun setHintTextString(editText: InputEditTextView, aInputType: Int?) {
    editText.setInputType(aInputType ?: InputType.TYPE_CLASS_TEXT)
}