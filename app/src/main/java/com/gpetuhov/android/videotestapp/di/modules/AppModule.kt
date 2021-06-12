package com.gpetuhov.android.videotestapp.di.modules

import android.content.Context
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.data.repository.VideoRepository
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(): Context = App.application.applicationContext

    @Provides
    @Singleton
    fun providesVideoRepository() = VideoRepository()

    @Provides
    @Singleton
    fun providesVideoUseCase(videoRepository: VideoRepository) =
        VideoUseCase(videoRepository)
}