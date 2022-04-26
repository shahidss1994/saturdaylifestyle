package com.shock.saturdaylifestyle.ui.main.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivityMainBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.main.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
        }
      //  mViewModel.getUsers(1)
    }

    override fun listenChannel() {
        mViewModel.eventFlow.onEach {
            when (it) {
                is MainActivityViewModel.Event.OnDataReceived -> {
                    Toast.makeText(this@MainActivity, "Api Call Success", Toast.LENGTH_SHORT).show()
                }
                is MainActivityViewModel.Event.OnBackPressed -> {
                    finish()
                }
            }
        }.observeInLifecycle(this@MainActivity)

    }
}
