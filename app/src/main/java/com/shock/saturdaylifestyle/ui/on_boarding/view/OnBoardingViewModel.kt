package com.shock.saturdaylifestyle.ui.on_boarding.view

/*import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.network.Resource
import com.shock.saturdaylifestyle.ui.main.viewModel.MainActivityViewModel
import com.shock.saturdaylifestyle.ui.main.network.MainRepository
import com.shock.saturdaylifestyle.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject*/

/*
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository)  {

    private val eventChannel = Channel<MainActivityViewModel.Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    fun getUsers(pageNo: Int) = viewModelScope.launch {
        val rs = mainRepository.getUsers(pageNo)
        if (rs is Resource.Success) {
//            viewState.welcomeMsg = "Data Received and click me to close"
//            onEvent(MainActivityViewModel.Event.OnDataReceived)
        }
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}*/
