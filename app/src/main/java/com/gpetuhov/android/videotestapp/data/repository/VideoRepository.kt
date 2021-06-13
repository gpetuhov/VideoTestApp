package com.gpetuhov.android.videotestapp.data.repository

import com.gpetuhov.android.videotestapp.data.source.remote.api.VideoApi
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.utils.Logger
import retrofit2.HttpException

class VideoRepository(private val videoApi: VideoApi) {

    suspend fun getVideoList(
        onSuccess: suspend (List<VideoInfo>) -> Unit,
        onError: suspend () -> Unit
    ) {
        try {
            val videoMetaData = videoApi.getMetaData()

            val videoList = listOf(
                VideoInfo(videoMetaData.results?.videoUrl1 ?: ""),
                VideoInfo(videoMetaData.results?.videoUrl2 ?: ""),
                VideoInfo(videoMetaData.results?.videoUrl3 ?: ""),
                VideoInfo(videoMetaData.results?.videoUrl4 ?: ""),
            )

            onSuccess(videoList)

        } catch (error: Exception) {
            error.printStackTrace()
            Logger.error("VideoRepository", error.message.toString())

            if (error is HttpException) {
                val responseString = error.response()?.errorBody()?.string() ?: ""
                Logger.error("Retrofit", responseString)
            }

            onError()
        }
    }
}