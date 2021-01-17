package com.hoang.moviedblearning.ui.screen.fragment.moviedetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.data.model.Movie
import com.hoang.moviedblearning.databinding.FragmentMoveiDetailBinding
import com.hoang.moviedblearning.ui.base.BaseFragment
import com.hoang.moviedblearning.utils.setSingleClick

class MovieDetailFragment :
    BaseFragment<FragmentMoveiDetailBinding, MovieDetailViewModel>(R.layout.fragment_movei_detail) {
    override val viewModel: MovieDetailViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

    companion object {
        const val TAG = "MovieDetailFragment"
        const val BUNDLE_MOVIE = "BUNDLE_MOVIE"
        fun newInstance(movie: Movie) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_MOVIE, movie)
            }
        }
    }

    override fun addEvent() {
        with(binding) {
            imageBack.setSingleClick {
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun initView() {
        initData()

    }

    private fun initData() {
        viewModel.movie.value = arguments?.getParcelable(BUNDLE_MOVIE)
    }
}