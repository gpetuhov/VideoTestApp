package com.gpetuhov.android.videotestapp.di.modules

import android.content.Context
import com.gpetuhov.android.videotestapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(): Context = App.application.applicationContext
}