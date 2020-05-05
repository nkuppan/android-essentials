package com.ancient.essentials.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes

/**
 * Created by ancientinc on 05/05/20.
 **/
abstract class BaseSplashScreenActivity : BaseActivity() {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        handler.postDelayed(
            {
                moveNext()
            },
            5000L
        )
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun moveNext()
}