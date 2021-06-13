package com.gpetuhov.android.videotestapp.domain.usecase

import com.gpetuhov.android.videotestapp.data.repository.VideoRepository
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo

class VideoUseCase(private val videoRepository: VideoRepository) {

    suspend fun getVideoList(
        onSuccess: suspend (List<VideoInfo>) -> Unit,
        onError: suspend () -> Unit
    ) {
        videoRepository.getVideoList(onSuccess, onError)
    }
}