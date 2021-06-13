package com.gpetuhov.android.videotestapp.presentation.video

import androidx.recyclerview.widget.DiffUtil
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo

class VideoDiffCallback : DiffUtil.ItemCallback<VideoInfo>() {
    override fun areItemsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: VideoInfo, newItem: VideoInfo): Boolean {
        return oldItem.url == newItem.url
    }
}