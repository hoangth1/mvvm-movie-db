package com.hoang.moviedblearning.ui.screen.fragment.main

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.databinding.FragmentMainBinding
import com.hoang.moviedblearning.ui.adapter.GenreAdapter
import com.hoang.moviedblearning.ui.base.BaseFragment
import com.hoang.moviedblearning.ui.screen.fragment.moviedetail.MovieDetailFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {
    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    lateinit var genreAdapter: GenreAdapter
    override fun addEvent() {
    }

    override fun initView() {
        viewModel.getGenres()
        observeLiveData()
        initGenreAdapter()
    }

    private fun observeLiveData() {
        with(viewModel) {
            listGenre.observe(viewLifecycleOwner, Observer {
                Log.d("kiemtra", "trigger $it")
                genreAdapter.submitList(
                    it
                )
            })
        }
    }

    private fun initGenreAdapter() {
        genreAdapter = GenreAdapter {
            addFragment(
                fragment = MovieDetailFragment.newInstance(it),
                tag = MovieDetailFragment.TAG
            )
        }
        with(binding) {
            recyclerGenre.apply {
                adapter = genreAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

}