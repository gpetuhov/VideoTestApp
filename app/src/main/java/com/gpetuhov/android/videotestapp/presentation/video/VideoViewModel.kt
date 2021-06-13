package com.gpetuhov.android.videotestapp.presentation.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import com.gpetuhov.android.videotestapp.utils.Logger
import kotlinx.coroutines.*
import javax.inject.Inject

class VideoViewModel : ViewModel() {

    @Inject lateinit var videoUseCase: VideoUseCase

    var isLoading: LiveData<Boolean>
    var videoList: LiveData<List<VideoInfo>>
    var loadError: LiveData<Boolean>

    private val _isLoading = MutableLiveData<Boolean>()
    private val _videoList = MutableLiveData<List<VideoInfo>>()
    private val _loadError = MutableLiveData<Boolean>()

    private var loadJob: Job? = null

    private var cachedVideoList: List<VideoInfo>? = null

    init {
        App.appComponent.inject(this)

        isLoading = _isLoading
        videoList = _videoList
        loadError = _loadError
    }

    override fun onCleared() {
        super.onCleared()
        loadJob?.cancel()
    }

    fun loadVideoList() {
        if (cachedVideoList != null) {
            _videoList.postValue(cachedVideoList)
            return
        }

        postLoading(true)

        loadJob = viewModelScope.launch(Dispatchers.IO) {
            videoUseCase.getVideoList(
                { videoList -> postVideoList(videoList) },
                { postError() }
            )
        }
    }

    fun resetError() = _loadError.postValue(false)

    private fun postLoading(isLoading: Boolean) = _isLoading.postValue(isLoading)

    private suspend fun postVideoList(videoList: List<VideoInfo>) {
        Logger.log("Video", videoList.toString())

        withContext(Dispatchers.Main) {
            postLoading(false)
            cachedVideoList = videoList
            _videoList.postValue(videoList)
        }
    }

    private suspend fun postError() {
        withContext(Dispatchers.Main) {
            postLoading(false)
            _loadError.postValue(true)
        }
    }
}