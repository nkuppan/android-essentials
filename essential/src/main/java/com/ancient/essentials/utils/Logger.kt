package com.ancient.essentials.utils

import android.util.Log
import androidx.databinding.library.BuildConfig

/**
 * Created by ancientinc on 22/04/20.
 **/
object Logger {

    private const val TAG = "Android Essentials"

    fun printError(aException: Throwable?) {
        if (BuildConfig.DEBUG && aException != null) {
            Log.e(TAG, if (aException.localizedMessage.isNullOrBlank()) "Unknown exception" else aException.localizedMessage)
        }
    }

    fun printInfo(aInformation: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, aInformation)
        }
    }

    fun printInfo(aTag: String, aInformation: String) {
        if (BuildConfig.DEBUG) {
            Log.i(aTag, aInformation)
        }
    }

    fun printWarning(aWarning: String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, aWarning)
        }
    }

    fun printVerbose(aVerbose: String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, aVerbose)
        }
    }

    fun printDebug(aDebug: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, aDebug)
        }
    }
}
