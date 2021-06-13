package com.gpetuhov.android.videotestapp.presentation.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.utils.Logger
import com.gpetuhov.android.videotestapp.utils.extensions.create
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter() : ListAdapter<VideoInfo, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

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
            player?.release()

            player = itemView.player_view.create(
                url = videoInfo.url,
                onError = { message -> Logger.error("Video", message) }
            )
        }
    }
}