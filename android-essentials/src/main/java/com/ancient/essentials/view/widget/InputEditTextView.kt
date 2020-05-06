/*
  Copyright @ Ancient Inc. All Rights Reserved.

  @author Naveen - PC on 1/8/2017.
 */

package com.ancient.essentials.view.widget

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import com.ancient.essentials.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout


@InverseBindingMethods(
    InverseBindingMethod(
        type = InputEditTextView::class,
        attribute = "app:editTextString",
        method = "getEditTextString"
    )
)
class InputEditTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var mEditTextView: MaterialAutoCompleteTextView

    lateinit var mTextInputLayout: TextInputLayout

    fun getEditTextString(): String {
        return mEditTextView.text.toString()
    }

    fun setEditTextString(aValue: String) {
        mEditTextView.setText(aValue)
    }

    init {

        inflateLayout(attrs)
    }

    private fun inflateLayout(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.widget_input_edit_text, this, true)

        mEditTextView = findViewById(R.id.widget_input_edit_text)
        mTextInputLayout = findViewById(R.id.widget_input_edit_text_container)

        orientation = VERTICAL

        readXMLValues(attrs)
    }

    private fun readXMLValues(attrs: AttributeSet?) {

        val lTypedArray = context.obtainStyledAttributes(attrs, R.styleable.InputEditTextView)

        val lTitleText = lTypedArray.getString(R.styleable.InputEditTextView_TitleText)
        val hintText = lTypedArray.getString(R.styleable.InputEditTextView_HintText)
        val lErrorText = lTypedArray.getString(R.styleable.InputEditTextView_ErrorText)
        val lInputType =
            lTypedArray.getInt(R.styleable.InputEditTextView_InputType, InputType.TYPE_CLASS_TEXT)

        val lTitleColor = lTypedArray.getColor(
            R.styleable.InputEditTextView_TitleTextColor,
            ContextCompat.getColor(context, R.color.colorAccent)
        )

        val lErrorColor = lTypedArray.getColor(
            R.styleable.InputEditTextView_ErrorTextColor,
            ContextCompat.getColor(context, R.color.red)
        )

        setTitleText(if (!lTitleText.isNullOrBlank()) lTitleText.toString() else "")
        setErrorText(if (!lErrorText.isNullOrBlank()) lErrorText.toString() else "")

        if (!hintText.isNullOrBlank()) {
            setHintText(hintText)
        }

        setTitleTextColor(lTitleColor)
        setErrorTextColor(lErrorColor)

        lTypedArray.recycle()

        setInputType(lInputType)
    }

    private fun setTitleText(aTitleValue: String) {
        mTextInputLayout.hint = (aTitleValue)
    }

    private fun setHintText(aTitleValue: String) {
        mEditTextView.hint = (aTitleValue)
    }

    fun setErrorText(aTitleValue: String?) {
        mTextInputLayout.isErrorEnabled = mEditTextView.text.isNullOrBlank()
        mTextInputLayout.error = if (aTitleValue.isNullOrBlank()) null else aTitleValue
    }

    private fun setErrorTextColor(aColor: Int) {
        val colorStateList = ColorStateList.valueOf(aColor)
        mTextInputLayout.setErrorTextColor(colorStateList)
    }

    private fun setTitleTextColor(aColor: Int) {
        val colorStateList = ColorStateList.valueOf(aColor)
        mTextInputLayout.hintTextColor = colorStateList
    }

    private fun setInputType(aInputType: Int) {
        mEditTextView.inputType = aInputType
    }

    fun setAutoCompleteAdapter(aAdapter: ArrayAdapter<*>) {
        mEditTextView.setAdapter(aAdapter)
    }
}
