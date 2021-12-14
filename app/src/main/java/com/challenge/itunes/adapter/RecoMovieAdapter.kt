package com.challenge.itunes.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.databinding.ItemTrendingMovieBinding
import com.challenge.itunes.utilities.extension.*
import com.challenge.itunes.utilities.openInBrowser
import com.challenge.itunes.utilities.randomizeColor
import java.lang.ref.WeakReference

class RecoMovieAdapter(private val context: Context): RecyclerView.Adapter<RecoMovieAdapter.ViewHolder>() {

    private var mDataList = listOf<RecoMoviesResponseResult>()

    inner class ViewHolder(private val bind: ItemTrendingMovieBinding): RecyclerView.ViewHolder(bind.root) {
        fun bindItem(data: RecoMoviesResponseResult) {
            bind.movieLogo.loadWithCornerRadius(data.artworkUrl100, 20)
            //Show custom placeholder if image url is empty
            if (data.artworkUrl100.isNullOrEmpty()) {
                showCustomPlaceholder(bind, data)
            }
            "$${data.trackPrice}".also { bind.moviePrice.text = it }
            bind.movieCategory.text = data.primaryGenreName
            bind.movieDesc.text = data.shortDesc ?: data.longDesc
            bind.movieName.text = data.trackName
            if (data.seeMore.isNullOrEmpty()) {
                bind.movieSeeMore.gone()
            } else {
                bind.movieSeeMore.visible()
                bind.movieSeeMore.setSafeOnClickListener {
                    openInBrowser(context, data.seeMore)
                }
                bind.root.setSafeOnClickListener {
                    openInBrowser(context, data.seeMore)
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

    //Gets the initials of the movie title and appends the artist below
    //Also generates a random background color to reduce the probability
    //of placeholder repetition.
    private fun showCustomPlaceholder(bind: ItemTrendingMovieBinding, data: RecoMoviesResponseResult) {
        bind.movieLogo.invisible()
        bind.movieLogoPlaceholder.visible()
        val placeholder = "${data.trackName?.getInitialLetters()}\n\nBY:\n${data.artistName?.getInitialLetters()}"
        bind.movieLogoPlaceholder.text = placeholder
        bind.movieLogoPlaceholder.setBackgroundColor(ContextCompat.getColor(context, randomizeColor()))
    }

    fun setData(data: List<RecoMoviesResponseResult>) {
        val recoDiffUtil = RecoDiffUtil(mDataList, data)
        val recoDiffResult = DiffUtil.calculateDiff(recoDiffUtil)
        mDataList = data
        recoDiffResult.dispatchUpdatesTo(this)
    }

}