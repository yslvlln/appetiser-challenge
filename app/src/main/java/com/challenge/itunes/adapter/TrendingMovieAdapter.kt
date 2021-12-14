package com.challenge.itunes.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.itunes.data.model.TrendMoviesResponseResult
import com.challenge.itunes.databinding.ItemTrendingMovieBinding
import com.challenge.itunes.utilities.extension.gone
import com.challenge.itunes.utilities.extension.loadWithCornerRadius
import com.challenge.itunes.utilities.extension.setSafeOnClickListener
import com.challenge.itunes.utilities.extension.visible

class TrendingMovieAdapter(private val context: Context): RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

    private var mDataList = emptyList<TrendMoviesResponseResult>()

    inner class ViewHolder(private val bind: ItemTrendingMovieBinding): RecyclerView.ViewHolder(bind.root) {
        fun bindItem(data: TrendMoviesResponseResult) {
            bind.movieLogo.loadWithCornerRadius(data.artworkUrl100, 20)
            bind.movieCategory.text = data.primaryGenreName
            bind.movieDesc.text = data.shortDesc ?: data.longDesc
            bind.movieName.text = data.trackName
            "$${data.trackPrice}".also { bind.moviePrice.text = it }
            if (data.seeMore.isNullOrEmpty()) {
                bind.movieSeeMore.gone()
            } else {
                bind.movieSeeMore.visible()
                bind.movieSeeMore.setSafeOnClickListener {
                    openInBrowser(data.seeMore)
                }
                bind.root.setSafeOnClickListener {
                    openInBrowser(data.seeMore)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mDataList[position]
        holder.bindItem(data)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    private fun openInBrowser(url: String) {
        val mUrl = url.replace("https://", "http://")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mUrl))
        startActivity(context, browserIntent, Bundle())
    }

    fun setData(data: List<TrendMoviesResponseResult>) {
        val toDoDiffUtil = TrendDiffUtil(mDataList, data)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        mDataList = data
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}