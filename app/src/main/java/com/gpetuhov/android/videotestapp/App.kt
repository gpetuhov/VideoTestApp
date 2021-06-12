package com.gpetuhov.android.videotestapp

import android.app.Application
import com.gpetuhov.android.videotestapp.di.components.AppComponent
import com.gpetuhov.android.videotestapp.di.components.DaggerAppComponent
import com.gpetuhov.android.videotestapp.utils.Logger

class App: Application() {

    companion object {
        lateinit var application: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        application = this
        appComponent = DaggerAppComponent.builder().build()

        Logger.init(application.applicationContext)
    }
}