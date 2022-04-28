package com.shock.saturdaylifestyle.ui.main.viewModel

import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LoginRegisterRepository
) : BaseViewModel(repository) {



}