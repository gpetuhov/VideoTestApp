package com.gpetuhov.android.videotestapp.di.components

import com.gpetuhov.android.videotestapp.di.modules.AppModule
import com.gpetuhov.android.videotestapp.di.modules.OkHttpModule
import com.gpetuhov.android.videotestapp.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        OkHttpModule::class,
    ]
)
@Singleton
interface AppComponent {
    // TODO: remove this
    fun inject(mainActivity: MainActivity)
}