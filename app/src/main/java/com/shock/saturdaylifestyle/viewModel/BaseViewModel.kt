package com.shock.saturdaylifestyle.viewModel

import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.network.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository? = null
) : ViewModel() {

    abstract fun onBackPressed()

}