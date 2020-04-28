package com.ancient.essentials.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.ancient.essentials.R

/**
 * Created by ancientinc on 22/04/20.
 **/
object AlertDialogUtils {

    /**
     * Method is used to create the common dialog utils method
     *
     * @param aContext          to create the dialog with the corresponding window
     * @param isNegativeAllow   it will decide the user need negative button option
     * @param aMessage          dialog message content
     * @param aTitle            title of the dialog
     * @param aCallback         to find positive button click in callback
     */
    fun createConformationDialog(
        aContext: Context,
        aTitle: String,
        aMessage: String,
        isNegativeAllow: Boolean,
        aCallback: ((Int) -> Unit)? = null
    ) {
        val localBuilder = AlertDialog.Builder(aContext)
        localBuilder.setTitle(aTitle)
        localBuilder.setMessage(aMessage)
        localBuilder.setCancelable(false)
        localBuilder.setPositiveButton(aContext.getString(R.string.ok)) { dialogInterface, paramInt ->
            dialogInterface.cancel()
            aCallback?.invoke(paramInt)
        }

        if (isNegativeAllow) {
            localBuilder.setNegativeButton(aContext.getString(R.string.cancel)) { dialogInterface, paramInt ->
                dialogInterface.cancel()
                aCallback?.invoke(paramInt)
            }
        }

        localBuilder.show()
    }
}