package com.gpetuhov.android.videotestapp.utils

import android.content.Context
import com.gpetuhov.android.videotestapp.BuildConfig
import timber.log.Timber

class Logger {
    companion object {
        fun init(applicationContext: Context) {
            if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        }

        fun log(tag: String, message: String) = Timber.tag(tag).d(message)

        fun error(tag: String, message: String) = Timber.tag(tag).e(message)
    }
}