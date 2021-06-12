package com.gpetuhov.android.videotestapp.data.repository

import com.gpetuhov.android.videotestapp.data.source.remote.api.VideoApi
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo

class VideoRepository(private val videoApi: VideoApi) {

    suspend fun getVideoList(): List<VideoInfo> {
        val videoMetaData = videoApi.getMetaData()

        return listOf(
            VideoInfo(videoMetaData.results?.videoUrl1 ?: ""),
            VideoInfo(videoMetaData.results?.videoUrl2 ?: ""),
            VideoInfo(videoMetaData.results?.videoUrl3 ?: ""),
            VideoInfo(videoMetaData.results?.videoUrl4 ?: ""),
        )
    }
}