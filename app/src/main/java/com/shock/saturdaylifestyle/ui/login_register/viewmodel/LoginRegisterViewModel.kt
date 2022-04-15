package com.shock.saturdaylifestyle.ui.login_register.viewmodel

import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.network.Resource
import com.shock.saturdaylifestyle.ui.main.MainRepository
import com.shock.saturdaylifestyle.ui.main.MainViewState
import com.shock.saturdaylifestyle.viewModel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    val viewState = MainViewState()

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    fun login() {
        viewModelScope.launch {
            val rs = mainRepository.loginUser(1)
            if (rs is Resource.Success) {
                viewState.welcomeMsg = "Data Received and click me to close"
                onEvent(Event.OnDataReceived)
            }
        }
    }

    fun register(name: String, phoneNumber: String, countryCode: String, genderType: Int, referral: String) {
        viewModelScope.launch {
            val rs = mainRepository.registerUser(name, phoneNumber, countryCode, genderType, referral)
            if (rs is Resource.Success) {
                viewState.welcomeMsg = "Data Received and click me to close"
                onEvent(Event.OnDataReceived)
            }
        }
    }


        override fun onBackPressed() {
            onEvent(Event.OnBackPressed)
        }

        private fun onEvent(event: Event) {
            viewModelScope.launch { eventChannel.send(event) }
        }

        sealed class Event {
            object OnBackPressed : Event()
            object OnDataReceived : Event()
        }
}