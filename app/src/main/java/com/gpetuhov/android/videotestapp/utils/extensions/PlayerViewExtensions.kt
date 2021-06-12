package com.gpetuhov.android.videotestapp.utils.extensions

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

fun PlayerView.create(
    url: String,
    isLoop: Boolean = false,
    playWhenReady: Boolean = true
): SimpleExoPlayer {
    val player = SimpleExoPlayer.Builder(context).build()

    if (isLoop) {
        player.repeatMode = Player.REPEAT_MODE_ALL
    }

    this.player = player

    val mediaItem = MediaItem.fromUri(url)
    player.setMediaItem(mediaItem)

//    val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
//        context,
//        Util.getUserAgent(context, context.packageName)
//    )
//
//    val videoSource = ProgressiveMediaSource
//        .Factory(dataSourceFactory)
//        .createMediaSource(MediaItem.fromUri(url))
//
//    player.setMediaSource(videoSource)
    player.playWhenReady = playWhenReady
    player.prepare()
    return player
}
