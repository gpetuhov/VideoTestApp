package com.gpetuhov.android.videotestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import com.gpetuhov.android.videotestapp.utils.Logger
import com.gpetuhov.android.videotestapp.utils.extensions.create
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // TODO: remove this
    @Inject lateinit var videoUseCase: VideoUseCase

    private var player: SimpleExoPlayer? = null

    init {
        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: move this into ViewModel
        GlobalScope.launch {
            val videoList = videoUseCase.getVideoList()
            Logger.log("Video",videoList.toString())

            withContext(Dispatchers.Main) {
                player = player_view.create(
                    url = videoList[0].url,
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