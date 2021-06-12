package com.gpetuhov.android.videotestapp

import android.app.Application

class App: Application() {

    companion object {
        lateinit var application: App
//        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        application = this
//        appComponent = DaggerAppComponent.builder().build()
    }
}