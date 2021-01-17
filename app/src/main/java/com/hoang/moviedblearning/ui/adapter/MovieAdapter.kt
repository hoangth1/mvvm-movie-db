package com.hoang.moviedblearning.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.data.model.Movie
import com.hoang.moviedblearning.databinding.ItemMovieBinding
import com.hoang.moviedblearning.ui.base.BaseRecyclerViewAdapter
import com.hoang.moviedblearning.utils.setSingleClick

class MovieAdapter(val onSelectMovie: (Movie) -> Unit) :
    BaseRecyclerViewAdapter<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }) {
    override val layoutRes: Int = R.layout.item_movie
    override fun bindData(itemBinding: ItemMovieBinding, position: Int) {
        itemBinding.root.setSingleClick {
            onSelectMovie(itemBinding.item ?: return@setSingleClick)
        }
    }
}