package com.gpetuhov.android.videotestapp.di.modules

import android.content.Context
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.data.repository.VideoRepository
import com.gpetuhov.android.videotestapp.data.source.remote.api.VideoApi
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.application.applicationContext

    @Provides
    @Singleton
    fun provideVideoRepository() = VideoRepository()

    @Provides
    @Singleton
    fun provideVideoUseCase(videoRepository: VideoRepository) =
        VideoUseCase(videoRepository)

    @Provides
    @Singleton
    fun provideVideoApi(retrofit: Retrofit): VideoApi =
        retrofit.create(VideoApi::class.java)
}