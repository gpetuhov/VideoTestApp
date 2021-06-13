package com.gpetuhov.android.videotestapp.utils.extensions

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.gpetuhov.android.videotestapp.utils.Logger
import com.gpetuhov.android.videotestapp.utils.video.UnsafeSimpleExoPlayerBuilder

fun PlayerView.create(
    url: String
): SimpleExoPlayer {
    val player = UnsafeSimpleExoPlayerBuilder(context)
        .build()

    this.player = player

    val mediaItem = MediaItem.fromUri(url)
    player.setMediaItem(mediaItem)

    player.addListener(object : Player.Listener {
        override fun onPlayerError(error: ExoPlaybackException) {
            Logger.log("Video", "ERROR!!!!!")
        }
    })

    player.playWhenReady = true
    player.prepare()

    return player
}
