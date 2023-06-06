package com.siklusdev.gempavr.ui.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.siklusdev.gempavr.databinding.FragmentCategoryBinding
import com.siklusdev.gempavr.model.CategoryModel
import com.siklusdev.gempavr.model.VideoModel
import com.siklusdev.gempavr.module.BaseFragmentBinding
import com.siklusdev.gempavr.ui.category.adapter.CategoryTabAdapter
import com.siklusdev.gempavr.ui.video.adapter.VideoAdapter
import kotlinx.android.synthetic.main.recycler_view_layout.view.*

class CategoryFragment :
    BaseFragmentBinding<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val args by navArgs<CategoryFragmentArgs>()

    private val adapterCategory by lazy(LazyThreadSafetyMode.NONE) {
        CategoryTabAdapter(
            ::onClickAdapterCategory
        )
    }

    private val adapterVideo by lazy(LazyThreadSafetyMode.NONE) {
        VideoAdapter(
            ::onClickAdapterVideo
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshCategory(args.id)

        binding.categoryRecycler.let {
            it.adapter = adapterCategory
            it.apply {
                isNestedScrollingEnabled = false
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }

        binding.list.recycler.let {
            it.adapter = adapterVideo
            it.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }

    private fun refreshCategory(category_id: Int) {

        adapterCategory.clear()
        adapterCategory.insertAll(CategoryModel().fetchCategory(), category_id)

        val videoList = VideoModel().fetchVideoByCategory(category_id)

        isLog("video list size : ${videoList.size}")

        adapterVideo.clear()
        adapterVideo.insertAll(videoList)

        binding.list.emptyLayout.isVisible = videoList.isEmpty()

//        binding.list.recycler.emptyLayout.isVisible = videoList.isEmpty()

    }

    private fun onClickAdapterCategory(item: CategoryModel) {
        refreshCategory(item.id)
    }

    private fun onClickAdapterVideo(item: VideoModel) {
        findNavController().navigate(CategoryFragmentDirections.actionToVideoDetail(item.id))
    }

    private fun isLog(msg: String) {
        Log.e("Category", msg)
    }

}