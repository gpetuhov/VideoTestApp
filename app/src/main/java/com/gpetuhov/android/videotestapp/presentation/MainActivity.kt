package com.gpetuhov.android.videotestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gpetuhov.android.videotestapp.App
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.usecase.VideoUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // TODO: remove this
    @Inject lateinit var videoUseCase: VideoUseCase

    init {
        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: remove this
        GlobalScope.launch {
            videoUseCase.getVideoList()
        }
    }
}