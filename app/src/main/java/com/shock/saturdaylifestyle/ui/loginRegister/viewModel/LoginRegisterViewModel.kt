package com.shock.saturdaylifestyle.ui.loginRegister.viewModel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.base.viewState.ColorViewState
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.CountryCodeNumberViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState
import com.shock.saturdaylifestyle.ui.main.network.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()
    val textWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.PHONE_NO)

    val viewState = LoginRegisterViewState()
    var phoneNo = ""

    init {
        setViewPagerListData()
        setCountryCodeNumberList()
    }

    fun onLoginCreateAccountClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT))
    }

    fun onContinueClicked() {
        if(phoneNo.length >= 8) {
            viewState.loginOrCreateAccountVisibility = false
        }
    }

    fun onSendOTPViaWhatsappClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER))
    }

    fun onSendOTPViaSMSClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.CONFIRM_YOUR_NUMBER))
    }

    fun onBackPressed() {
        onEvent(Event.OnBackPressed)
    }

    fun showCountryCodePicker() {
        onEvent(Event.PickerDialog)
    }

    fun onChooseVerificationBackClicked() {
        viewState.loginOrCreateAccountVisibility = true
    }

    fun checkSelectedCountry(countryCodeNumberViewState: CountryCodeNumberViewState) {
        viewState.countryCodeNumberViewState = countryCodeNumberViewState
        onEvent(Event.PickerDialogClose)
    }

    private fun onEvent(event: Event) {
        viewModelScope.launch { eventChannel.send(event) }
    }

    sealed class Event {
        object OnBackPressed : Event()
        object PickerDialog : Event()
        object PickerDialogClose : Event()
        data class NavigateTo(val navigateTo: String) : Event()
    }

    private fun setViewPagerListData() {
        val arrayList = arrayListOf<IntroViewPagerItemViewState>()
        val introViewPagerItemViewState1 = IntroViewPagerItemViewState(
            1,
            "ANYWHERE IN THE WORLD!",
            "Enjoy free shipping all across the globe\nonly for you!",
            DrawableViewState(R.mipmap.iv_onboarding1)
        )
        arrayList.add(introViewPagerItemViewState1)

        val introViewPagerItemViewState2 = IntroViewPagerItemViewState(
            2,
            "PRICE INCLUDES\nPRESCRIPTION LENSES",
            "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState2)

        val introViewPagerItemViewState3 = IntroViewPagerItemViewState(
            3,
            "HOME TRY ON",
            "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState3)

        val introViewPagerItemViewState4 = IntroViewPagerItemViewState(
            4,
            "SAVE MORE BUCKS!",
            "Enjoy our exclusive promos as well as our\nloyalty program to save some more!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState4)

        val introViewPagerItemViewState5 = IntroViewPagerItemViewState(
            5,
            "OFFLINE STORES",
            "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia.",
            DrawableViewState(R.mipmap.iv_onboarding5)
        )
        arrayList.add(introViewPagerItemViewState5)
        viewState.introViewPagerItemViewStateList = arrayList
    }

    private fun setCountryCodeNumberList() {
        val arrayList = arrayListOf<CountryCodeNumberViewState>()
        arrayList.add(CountryCodeNumberViewState(1, "+91", "India"))
        arrayList.add(CountryCodeNumberViewState(2, "+92", "Pakistan"))
        arrayList.add(CountryCodeNumberViewState(3, "+1", "United States"))
        arrayList.add(CountryCodeNumberViewState(4, "+11", "United Kingdom"))
        arrayList.add(CountryCodeNumberViewState(5, "+42", "Australia"))
        arrayList.add(CountryCodeNumberViewState(6, "+62", "Indonesia"))

        viewState.countryCodeNumberViewStateList = arrayList
    }

    inner class LoginRegisterTextWatcher(private val textWatcherType: String) : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.toString()?.let {
                if (textWatcherType == Constants.TextWatcherType.PHONE_NO) {
                    phoneNo = it
                    if(it.isNotEmpty()){
                        if(it.length < 8){
                            viewState.phoneNoMessageColorViewState = ColorViewState(R.color.warning_orange)
                            viewState.continueBtnDrawableViewState = DrawableViewState(R.drawable.bg_button3)
                        } else {
                            viewState.phoneNoMessageColorViewState = ColorViewState(R.color.success_green)
                            viewState.continueBtnDrawableViewState = DrawableViewState(R.drawable.bg_button2)
                        }
                        viewState.showPhoneNoMessage = true
                    } else {
                        viewState.showPhoneNoMessage = false
                        viewState.continueBtnDrawableViewState = DrawableViewState(R.drawable.bg_button3)
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

}