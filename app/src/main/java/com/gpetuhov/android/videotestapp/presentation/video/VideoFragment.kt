package com.gpetuhov.android.videotestapp.presentation.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.gpetuhov.android.videotestapp.R
import com.gpetuhov.android.videotestapp.domain.model.VideoInfo
import com.gpetuhov.android.videotestapp.utils.extensions.setVisible
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    private lateinit var videoAdapter: VideoAdapter

    private val viewModel by viewModels<VideoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVideoList()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.loadVideoList()
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading -> updateLoadingUI(isLoading) })
        viewModel.videoList.observe(viewLifecycleOwner, { videoList -> updateVideoListUI(videoList) })
        viewModel.loadError.observe(viewLifecycleOwner, { isError -> updateErrorUI(isError) })
    }

    private fun initVideoList() {
        video_list.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        videoAdapter = VideoAdapter()
        video_list.adapter = videoAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(video_list)
    }

    private fun updateLoadingUI(isLoading: Boolean) =
        showProgress(isLoading)

    private fun updateVideoListUI(videoList: List<VideoInfo>) {
        showVideoList(false)
        videoAdapter.submitList(videoList)
    }

    private fun updateErrorUI(isError: Boolean) {
        if (isError) {
            showVideoList(true)
            viewModel.resetError()
        }
    }

    private fun showVideoList(isError: Boolean) {
        video_list.setVisible(!isError)
        video_list_error.setVisible(isError)
    }

    private fun showProgress(isVisible: Boolean) =
        video_list_load_progress.setVisible(isVisible)
}