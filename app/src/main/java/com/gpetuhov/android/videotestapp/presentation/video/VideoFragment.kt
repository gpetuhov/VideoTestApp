package com.gpetuhov.android.videotestapp.presentation.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.R

class VideoFragment : Fragment() {

    private var player: SimpleExoPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        player = player_view.create(
//            url = videoList[1].url,
//            onError = { message -> Logger.error("Video", message) }
//        )

        // TODO: show metadata loading progress
        // TODO: show metadata load error
        // TODO: do not reload meta data on screen rotation
        // TODO: add video list
        // TODO: show player errors

    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}