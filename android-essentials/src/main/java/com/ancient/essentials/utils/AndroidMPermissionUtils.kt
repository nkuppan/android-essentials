package com.ancient.essentials.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * This class is used to handle android M permissions utilities and
 *
 * Created by ancientinc on 29/04/20.
 **/
object AndroidMPermissionUtils {

    private var mPermissionRationaleOption: MutableMap<String, Boolean>? = null

    private fun checkPermissionsStatus(
        aContext: Context,
        aPermissionList: Array<String>
    ): Array<String> {

        val missedPermission = arrayListOf<String>()

        for (lPermission in aPermissionList) {
            if (ContextCompat.checkSelfPermission(
                    aContext,
                    lPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                missedPermission.add(lPermission)
            }
        }

        return missedPermission.toTypedArray()
    }


    private fun checkPermissionRationle(aActivity: Activity, aPermissionList: Array<String>) {

        mPermissionRationaleOption = mutableMapOf()

        for (permission in aPermissionList) {
            mPermissionRationaleOption?.put(
                permission,
                ActivityCompat.shouldShowRequestPermissionRationale(
                    aActivity, permission
                )
            )
        }
    }

    /**
     * Requesting permission list which is granted or not by user. If the user doesn't allowed
     * a specific permissions will request permission for that specific permission
     * [Manifest.permission] to read permission.
     *
     * @param aActivity to check permission status and request
     * @param aPermissionList list to be checked and request
     * @param aRequestCode permission result code to be handle in [Activity.onRequestPermissionsResult]
     * @param callRequest to call [ActivityCompat.requestPermissions] or not
     * @return true if all permissions are allowed to access else false
     */
    private fun requestPermission(
        aActivity: Activity,
        aPermissionList: Array<String>,
        aRequestCode: Int,
        callRequest: Boolean = false
    ): Boolean {

        val lRemainingPermissions = checkPermissionsStatus(aActivity, aPermissionList)

        if (lRemainingPermissions.isNotEmpty()) {

            if (callRequest) {

                checkPermissionRationle(aActivity, aPermissionList)

                ActivityCompat.requestPermissions(
                    aActivity,
                    lRemainingPermissions,
                    aRequestCode
                )
            }

            return false
        }

        return true
    }

    /**
     * Method is used to validate the [Activity.onRequestPermissionsResult] values returned from
     * the user actions.
     *
     * @param aPermissions list to be checked
     * @param aGrantResults returned granted result from [Activity.onRequestPermissionsResult]
     * @return will send the set of permission which are not permitted
     */
    fun checkPermissionResult(
        aPermissions: Array<out String>,
        aGrantResults: IntArray
    ): List<String> {

        val notPermittedValues = arrayListOf<String>()

        if (aPermissions.isNotEmpty()) {
            aPermissions.forEachIndexed { aPosition, aValue ->
                if (aGrantResults[aPosition] != PackageManager.PERMISSION_GRANTED) {
                    notPermittedValues.add(aValue)
                }
            }
        }

        return notPermittedValues
    }
}