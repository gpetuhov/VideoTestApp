package com.gpetuhov.android.videotestapp.presentation.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.utils.extensions.create
import com.gpetuhov.android.videotestapp.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter : ListAdapter<VideoInfo, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoInfo = getItem(position)
        holder.bind(videoInfo)
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var player: SimpleExoPlayer? = null

        fun bind(videoInfo: VideoInfo) {
            onBindStart()

            player?.release()

            player = itemView.player_view.create(
                url = videoInfo.url,
                onVideoReady = { onSuccess() },
                onError = { message -> onError(message) }
            )
        }

        private fun onBindStart() {
            showProgress(true)
            showPlayer(false)
            showError(false)
        }

        private fun onSuccess() {
            showProgress(false)
            showPlayer(true)
            showError(false)
        }

        private fun onError(message: String) {
            showProgress(false)
            showPlayer(false)
            showError(true, message)
        }

        private fun showPlayer(isVisible: Boolean) =
            itemView.player_view.setVisible(isVisible)

        private fun showError(isVisible: Boolean, message: String = "") {
            itemView.player_error.setVisible(isVisible)
            itemView.player_error.text = message
        }

        private fun showProgress(isVisible: Boolean) =
            itemView.player_load_progress.setVisible(isVisible)
    }
}