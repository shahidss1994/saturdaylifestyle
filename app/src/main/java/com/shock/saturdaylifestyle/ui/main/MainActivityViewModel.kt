package com.shock.saturdaylifestyle.ui.main

/*
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.network.Resource
import com.shock.saturdaylifestyle.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    val viewState = MainViewState()

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    fun getUsers(pageNo: Int) = viewModelScope.launch {
        val rs = mainRepository.getUsers(pageNo)
        if (rs is Resource.Success) {
            viewState.welcomeMsg = "Data Received and click me to close"
            onEvent(Event.OnDataReceived)
        }
    }

    override fun onBackPressed() {
        onEvent(Event.OnBackPressed)
    }

    private fun onEvent(event: Event){
        viewModelScope.launch { eventChannel.send(event) }
    }

    sealed class Event{
        object OnBackPressed : Event()
        object OnDataReceived : Event()
    }

}*/
