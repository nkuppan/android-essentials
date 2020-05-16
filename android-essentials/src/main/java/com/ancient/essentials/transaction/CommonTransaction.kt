package com.ancient.essentials.transaction

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ancientinc on 12/04/20.
 **/
object CommonTransaction {

    fun getRetrofitObject(aContext: Context, aBaseURL: String): Retrofit {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(aContext.cacheDir, cacheSize)

        val client = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(aContext)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val dateFormat: Gson = GsonBuilder()
            .setDateFormat("MMM dd, yyyy HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl(aBaseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(dateFormat))
            .build()
    }

    fun hasNetwork(aContext: Context): Boolean? {
        val connectivityManager =
            aContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}