package com.siklusdev.gempavr.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siklusdev.gempavr.databinding.ItemVideoBinding
import com.siklusdev.gempavr.extensions.loadUrlRounded
import com.siklusdev.gempavr.model.VideoModel

class SearchVideoAdapter(
    private val onClick: (VideoModel) -> Unit
) : RecyclerView.Adapter<SearchVideoAdapter.ViewHolder>() {

    var listData: MutableList<VideoModel> = ArrayList()

    fun insertAll(data: List<VideoModel>) {
        data.forEach {
            listData.add(it)
            notifyItemInserted(listData.size - 1)
        }
    }

    fun clear() {
        if (listData.isNotEmpty()) {
            listData.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]
        holder.bindTo(item)

        holder.itemView.setOnClickListener {
            onClick(item)
        }

    }

    inner class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(it: VideoModel) {

            binding.categoryTv.text = it.category.name
            binding.coverImg.loadUrlRounded(it.banner)
            binding.titleTv.text = it.title
            binding.descriptionTv.text = it.description

        }

    }

}