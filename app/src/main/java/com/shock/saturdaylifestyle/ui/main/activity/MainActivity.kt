package com.shock.saturdaylifestyle.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivityMainBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding().apply {
            bottomNavigationView.setupWithNavController(findNavController(R.id.navHostFragmentMain))
        }
    }

    override fun listenChannel() {

    }

}