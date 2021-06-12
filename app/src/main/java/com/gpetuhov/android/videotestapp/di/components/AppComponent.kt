package com.gpetuhov.android.videotestapp.di.components

import com.gpetuhov.android.videotestapp.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    // TODO: inject
}