package com.gpetuhov.android.videotestapp.data.source.remote.api

import com.gpetuhov.android.videotestapp.data.source.remote.models.VideoMetaData
import retrofit2.http.GET

interface VideoApi {
    @GET("/test/item")
    suspend fun getMetaData(): VideoMetaData
}