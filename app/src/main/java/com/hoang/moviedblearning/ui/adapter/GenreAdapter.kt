package com.hoang.moviedblearning.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.data.model.Genre
import com.hoang.moviedblearning.data.model.Movie
import com.hoang.moviedblearning.databinding.ItemGenreBinding
import com.hoang.moviedblearning.ui.base.BaseRecyclerViewAdapter

class GenreAdapter(val onSelectMovie: (Movie) -> Unit) :
    BaseRecyclerViewAdapter<Genre, ItemGenreBinding>(
        object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.name == newItem.name && oldItem.listMovie.size == newItem.listMovie.size
            }
        }
    ) {
    override val layoutRes: Int = R.layout.item_genre
    override fun bindData(itemBinding: ItemGenreBinding, position: Int) {
        super.bindData(itemBinding, position)
        val adapter = MovieAdapter {
            onSelectMovie(it)
        }
        itemBinding.recyclerMovie.adapter = adapter
        adapter.submitList(getItem(position).listMovie)
    }
}