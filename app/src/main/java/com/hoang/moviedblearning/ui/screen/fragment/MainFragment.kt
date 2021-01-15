package com.hoang.moviedblearning.ui.screen.fragment

import androidx.lifecycle.ViewModelProvider
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.databinding.FragmentMainBinding
import com.hoang.moviedblearning.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {
    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    override fun addEvent() {
    }

    override fun initView() {
    }

}