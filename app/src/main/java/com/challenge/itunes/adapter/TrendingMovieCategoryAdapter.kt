package com.challenge.itunes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.challenge.itunes.databinding.ItemTrendingCategoryMovieBinding
import com.challenge.itunes.utilities.constant.categories
import com.challenge.itunes.utilities.extension.setSafeOnClickListener
import com.challenge.itunes.utilities.randomizeColor

class TrendingMovieCategoryAdapter(
    private val context: Context,
    private val callback: CategoryCallback
): RecyclerView.Adapter<TrendingMovieCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val bind: ItemTrendingCategoryMovieBinding): RecyclerView.ViewHolder(bind.root) {
        fun bindItem(data: String) {
            bind.trendingCategoryName.text = data
            bind.trendingCategoryCv.setCardBackgroundColor(ContextCompat.getColor(context, randomizeColor()))
            bind.root.setSafeOnClickListener {
                callback.onCategoryClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingCategoryMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = categories[position]
        holder.bindItem(data)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface CategoryCallback {
        fun onCategoryClick(category: String)
    }

}