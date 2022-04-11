package com.shock.saturdaylifestyle.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivityMainBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

/**
 *
 * Sample free api
 * GET -> https://reqres.in/api/users?page=1
 *
 */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
            mViewModel.viewState.welcomeMsg = "Api will call soon"
        }
        mViewModel.getUsers(1)
    }

    override fun listenChannel(){
        mViewModel.eventFlow.onEach {
            when(it){
                MainActivityViewModel.Event.OnDataReceived -> {
                    Toast.makeText(this@MainActivity, "Api Call Success", Toast.LENGTH_SHORT).show()
                }
                MainActivityViewModel.Event.OnBackPressed -> {
                    finish()
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.activity_main
}