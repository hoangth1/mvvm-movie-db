package com.hoang.moviedblearning.ui.screen.activity

import android.os.Bundle
import com.hoang.moviedblearning.R
import com.hoang.moviedblearning.ui.base.BaseActivity
import com.hoang.moviedblearning.ui.screen.fragment.main.MainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(MainFragment.newInstance(), false)
    }
}