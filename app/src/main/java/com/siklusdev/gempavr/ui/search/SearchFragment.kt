package com.siklusdev.gempavr.ui.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.siklusdev.gempavr.databinding.FragmentSearchBinding
import com.siklusdev.gempavr.model.VideoModel
import com.siklusdev.gempavr.module.BaseFragmentBinding
import com.siklusdev.gempavr.ui.search.adapter.SearchVideoAdapter

class SearchFragment : BaseFragmentBinding<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchVideoAdapter(
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

        binding.searchBtn.setOnClickListener {
            val keyword = binding.searchEd.text.toString()
        }

    }

    private fun searchReload() {



    }

    private fun onClickAdapter(it: VideoModel) {

    }

}