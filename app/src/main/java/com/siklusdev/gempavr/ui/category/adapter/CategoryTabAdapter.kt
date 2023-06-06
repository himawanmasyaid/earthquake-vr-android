package com.siklusdev.gempavr.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siklusdev.gempavr.R
import com.siklusdev.gempavr.databinding.ItemTabCategoryBinding
import com.siklusdev.gempavr.model.CategoryModel

class CategoryTabAdapter(
    private val onClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryTabAdapter.ViewHolder>() {

    var listData: MutableList<CategoryModel> = ArrayList()

    var SELECTE_ID = 0

    fun insertAll(data: List<CategoryModel>, category_id: Int) {
        SELECTE_ID = category_id
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
            ItemTabCategoryBinding.inflate(
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

    inner class ViewHolder(val binding: ItemTabCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: CategoryModel) {

            binding.categoryTv.setBackgroundResource(if (SELECTE_ID == item.id) R.drawable.bg_tab_category_selected else R.drawable.bg_tab_category)
            binding.categoryTv.text = item.name

        }

    }

}