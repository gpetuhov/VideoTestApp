package com.gpetuhov.android.videotestapp.di.modules

import com.gpetuhov.android.videotestapp.data.source.local.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverters: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_HOST)
            .addConverterFactory(gsonConverters)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverters(): GsonConverterFactory =
        GsonConverterFactory.create()
}