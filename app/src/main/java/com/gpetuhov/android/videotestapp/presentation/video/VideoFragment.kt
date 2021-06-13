package com.gpetuhov.android.videotestapp.presentation.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.utils.Logger
import com.gpetuhov.android.videotestapp.utils.extensions.create
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    private val viewModel by viewModels<VideoViewModel>()

    private var player: SimpleExoPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()

        // TODO: show metadata loading progress
        // TODO: show metadata load error
        // TODO: add video list
        // TODO: show player errors
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }

    private fun subscribeViewModel() {
        viewModel.loadVideoList()
        viewModel.videoList.observe(viewLifecycleOwner, { videoList -> initVideoList(videoList) })
    }

    private fun initVideoList(videoList: List<VideoInfo>) {
        player = player_view.create(
            url = videoList[1].url,
            onError = { message -> Logger.error("Video", message) }
        )
    }
}