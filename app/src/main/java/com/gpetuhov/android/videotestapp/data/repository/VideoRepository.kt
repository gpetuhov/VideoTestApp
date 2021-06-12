package com.gpetuhov.android.videotestapp.data.repository

import com.gpetuhov.android.videotestapp.data.source.remote.api.VideoApi
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo

class VideoRepository(private val videoApi: VideoApi) {

    suspend fun getVideoList(): List<VideoInfo> {
        val videoMetaData = videoApi.getMetaData()

        val first = videoMetaData.results?.videoUrl1

        // TODO

        return emptyList()
    }
}