package com.siklusdev.gempavr.ui.video

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.siklusdev.gempavr.databinding.FragmentVideoDetailBinding
import com.siklusdev.gempavr.extensions.loadUrl
import com.siklusdev.gempavr.model.VideoModel
import com.siklusdev.gempavr.module.BaseFragmentBinding
import com.siklusdev.gempavr.ui.video.adapter.VideoRelatedAdapter

class VideoDetailFragment :
    BaseFragmentBinding<FragmentVideoDetailBinding>(FragmentVideoDetailBinding::inflate) {

    private val args by navArgs<VideoDetailFragmentArgs>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        VideoRelatedAdapter(
            ::onClickAdapter
        )
    }

    private var SELECTED_ID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.let {
            it.backBarImg.setOnClickListener {
                findNavController().popBackStack()
            }
            it.titleBarTv.text = "Informasi VR"
        }

        fetchDetailVideo(args.id)

        binding.list.recycler.let {
            it.adapter = adapter
            it.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }

        binding.bannerImg.setOnClickListener {
            findNavController().navigate(VideoDetailFragmentDirections.actionToPlayVr(SELECTED_ID))
        }

    }

    private fun fetchDetailVideo(id: Int) {

        SELECTED_ID = id

        val video = VideoModel().fetchDetailVideo(id)

        binding.bannerImg.loadUrl(video.banner)
        binding.titleTv.text = video.title
        binding.categoryTv.text = video.category.name
        binding.descriptionTv.text = video.description

        adapter.clear()
        adapter.insertAll(VideoModel().fetchVideoByCategory(video.category.id))

    }

    private fun onClickAdapter(item: VideoModel) {
        fetchDetailVideo(item.id)
    }


}