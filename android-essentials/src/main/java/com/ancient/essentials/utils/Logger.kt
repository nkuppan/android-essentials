package com.ancient.essentials.utils

import android.app.Application
import android.util.Log

/**
 * Created by ancientinc on 22/04/20.
 */

object Logger {

    private var DEBUG = false
    private var TAG = "Android Essentials"

    fun init(aApplication: Application, isDebug: Boolean) {
        DEBUG = isDebug
        TAG = aApplication.packageName
    }

    fun printError(aException: Throwable?) {
        if (DEBUG && aException != null) {
            Log.e(
                TAG,
                if (aException.localizedMessage.isNullOrBlank()) "Unknown exception" else aException.localizedMessage
            )
        }
    }

    fun printInfo(aInformation: String) {
        if (DEBUG) {
            Log.i(TAG, aInformation)
        }
    }

    fun printInfo(aTag: String, aInformation: String) {
        if (DEBUG) {
            Log.i(aTag, aInformation)
        }
    }

    fun printWarning(aWarning: String) {
        if (DEBUG) {
            Log.w(TAG, aWarning)
        }
    }

    fun printVerbose(aVerbose: String) {
        if (DEBUG) {
            Log.v(TAG, aVerbose)
        }
    }

    fun printDebug(aDebug: String) {
        if (DEBUG) {
            Log.d(TAG, aDebug)
        }
    }
}
