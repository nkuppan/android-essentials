package com.ancient.essentials.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

/**
 * Created by ancientinc on 28/05/20.
 **/
object CryptoPreference {


    fun initialize(aContext: Context, aPrefName: String): SharedPreferences {

        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            EncryptedSharedPreferences.create(
                aPrefName,
                masterKeyAlias,
                aContext,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } else {
            aContext.getSharedPreferences(aPrefName, Context.MODE_PRIVATE)
        }
    }
}