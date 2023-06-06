package com.siklusdev.gempavr.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.siklusdev.gempavr.R
import com.siklusdev.gempavr.databinding.FragmentHomeBinding
import com.siklusdev.gempavr.extensions.toast
import com.siklusdev.gempavr.model.CategoryModel
import com.siklusdev.gempavr.model.VideoModel
import com.siklusdev.gempavr.module.BaseFragmentBinding
import com.siklusdev.gempavr.ui.video.adapter.VideoAdapter

class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        VideoAdapter(
            ::onClickAdapter
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.recycler.let {
            it.adapter = adapter
            it.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        binding.searchLayout.setOnClickListener {
            requireActivity().toast(getString(R.string.under_construction))
//            findNavController().navigate(HomeFragmentDirections.actionToSearch())
        }

        // category
        binding.publicSpaceLayout.setOnClickListener {
            val data = CategoryModel().categoryPublicSpace()
            directCategoryDetail(data.id)
        }

        binding.homeLayout.setOnClickListener {
            val data = CategoryModel().categoryHouse()
            directCategoryDetail(data.id)
        }

        binding.outdoorLayout.setOnClickListener {
            val data = CategoryModel().categoryOutdoor()
            directCategoryDetail(data.id)
        }

        binding.transportLayout.setOnClickListener {
            val data = CategoryModel().categoryTransportation()
            directCategoryDetail(data.id)
        }

        adapter.clear()
        adapter.insertAll(VideoModel().fetchVideo())

    }

    private fun directCategoryDetail(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionToCategory(id))
    }

    private fun onClickAdapter(data: VideoModel) {
        findNavController().navigate(HomeFragmentDirections.actionToVideoDetail(data.id))
    }

}