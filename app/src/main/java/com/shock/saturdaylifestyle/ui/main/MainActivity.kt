package com.shock.saturdaylifestyle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivityMainBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import kotlinx.coroutines.flow.onEach

/**
 *
 * Sample free api
 * GET -> https://reqres.in/api/users?page=1
 *
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenChannel()
        viewModel.getUsers(1)
        binding().apply {

        }
    }

    private fun listenChannel(){
        viewModel.eventFlow.onEach {
            when(it){
                MainActivityViewModel.Event.OnDataReceived -> {

                }
                MainActivityViewModel.Event.OnBackPressed -> {
                    finish()
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.activity_main
}