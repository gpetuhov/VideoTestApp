package com.gpetuhov.android.videotestapp.utils.video

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.gpetuhov.android.videotestapp.App
import okhttp3.OkHttpClient
import javax.inject.Inject

class UnsafeSimpleExoPlayerBuilder(context: Context) {

    @Inject lateinit var okHttpClient: OkHttpClient

    private val builder: SimpleExoPlayer.Builder

    init {
        App.appComponent.inject(this)

        val okHttpDataSourceFactory = OkHttpDataSource.Factory(okHttpClient)

        builder = SimpleExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(okHttpDataSourceFactory))
    }

    fun build() = builder.build()
}