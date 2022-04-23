package com.shock.saturdaylifestyle.ui.base.viewModel

import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.network.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository? = null
) : ViewModel() {

}