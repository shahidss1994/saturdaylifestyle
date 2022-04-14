package com.shock.saturdaylifestyle.ui.splash

import com.shock.saturdaylifestyle.ui.main.MainRepository
import com.shock.saturdaylifestyle.ui.common.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}