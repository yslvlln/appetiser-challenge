package com.challenge.itunes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.challenge.itunes.data.model.TrendMoviesResponseResult

class TrendDiffUtil(
    private val oldList: List<TrendMoviesResponseResult>,
    private val newList: List<TrendMoviesResponseResult>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].artworkUrl100 == newList[newItemPosition].artworkUrl100 &&
                oldList[oldItemPosition].artworkUrl60 == newList[newItemPosition].artworkUrl60 &&
                oldList[oldItemPosition].artworkUrl30 == newList[newItemPosition].artworkUrl30 &&
                oldList[oldItemPosition].primaryGenreName == newList[newItemPosition].primaryGenreName &&
                oldList[oldItemPosition].trackName == newList[newItemPosition].trackName &&
                oldList[oldItemPosition].trackPrice == newList[newItemPosition].trackPrice
    }
}