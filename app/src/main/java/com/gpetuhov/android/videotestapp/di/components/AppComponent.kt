package com.gpetuhov.android.videotestapp.di.components

import com.gpetuhov.android.videotestapp.di.modules.AppModule
import com.gpetuhov.android.videotestapp.di.modules.NetModule
import com.gpetuhov.android.videotestapp.di.modules.OkHttpModule
import com.gpetuhov.android.videotestapp.presentation.video.VideoFragment
import com.gpetuhov.android.videotestapp.utils.video.UnsafeSimpleExoPlayerBuilder
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        OkHttpModule::class,
        NetModule::class,
    ]
)
@Singleton
interface AppComponent {
    // TODO: remove this
    fun inject(videoFragment: VideoFragment)

    fun inject(unsafeSimpleExoPlayerBuilder: UnsafeSimpleExoPlayerBuilder)
}