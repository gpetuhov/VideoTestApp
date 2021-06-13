package com.gpetuhov.android.videotestapp.utils.extensions

import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.utils.video.UnsafeSimpleExoPlayerBuilder
import java.net.HttpURLConnection

fun PlayerView.create(
    url: String,
    isLoop: Boolean = false,
    playWhenReady: Boolean = false,
    onVideoReady: () -> Unit,
    onError: (String) -> Unit
): SimpleExoPlayer {
    val player = UnsafeSimpleExoPlayerBuilder(context)
        .build()

    this.player = player

    val mediaItem = MediaItem.fromUri(url)
    player.setMediaItem(mediaItem)

    player.addListener(getPlayerListener(onVideoReady, onError))

    player.playWhenReady = playWhenReady

    if (isLoop) {
        player.repeatMode = Player.REPEAT_MODE_ALL
    }

    player.prepare()

    return player
}

private fun PlayerView.getPlayerListener(
    onVideoReady: () -> Unit,
    onError: (String) -> Unit
): Player.Listener {
    return object : Player.Listener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            if (playbackState == Player.STATE_READY) {
                onVideoReady()
            }
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            val errorMessageId = when (error.type) {
                ExoPlaybackException.TYPE_SOURCE -> {
                    val cause = error.cause

                    if (
                        cause is HttpDataSource.InvalidResponseCodeException
                        && cause.responseCode == HttpURLConnection.HTTP_NOT_FOUND
                    ) {
                        R.string.file_not_found
                    } else {
                        R.string.server_error
                    }
                }

                else -> R.string.player_error
            }

            onError(context.getString(errorMessageId))
        }
    }
}