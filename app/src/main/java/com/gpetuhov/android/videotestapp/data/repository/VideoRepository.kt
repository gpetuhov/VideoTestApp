package com.gpetuhov.android.videotestapp.data.repository

import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import kotlinx.coroutines.delay

class VideoRepository {

    suspend fun getVideoList(): List<VideoInfo> {
        // TODO
        delay(5000)

        return emptyList()
    }
}