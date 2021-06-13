package com.gpetuhov.android.videotestapp.presentation.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import com.gpetuhov.android.videotestapp.utils.Logger
import com.gpetuhov.android.videotestapp.utils.extensions.create
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideoFragment : Fragment() {

    // TODO: remove this
    @Inject lateinit var videoUseCase: VideoUseCase

    private var player: SimpleExoPlayer? = null

    init {
        App.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: move this into ViewModel
        GlobalScope.launch {
            val videoList = videoUseCase.getVideoList()
            Logger.log("Video",videoList.toString())

            withContext(Dispatchers.Main) {
                player = player_view.create(
                    url = videoList[1].url,
                    onError = { message -> Logger.error("Video", message) }
                )
            }
        }

    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}