package com.shock.saturdaylifestyle.ui.loginRegister.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.Resource
import com.shock.saturdaylifestyle.ui.base.others.bind
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
//import com.shock.saturdaylifestyle.ui.base.viewModel.DrawableViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.CountryCodeNumberViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val repository: LoginRegisterRepository
) : BaseViewModel(repository) {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    val viewState = LoginRegisterViewState()

    init {
        setViewPagerListData()
        setCountryCodeNumberList()
    }


    fun sendOtpViaMissedCallApi(key: String, phoneNumber: String, countryCode: String) = viewModelScope.launch {
           val rs = repository.sendOtpViaMissedCall(key, phoneNumber, countryCode)
            if (rs is Resource.Success) {

                if (rs.value.status == true) {

                    onEvent(Event.NavigateTo(Constants.NavigateTo.MISSED_CALL_VERIFY_YOUR_NUMBER))
                }else
                {

                }
                // onEvent(Event.OnSendOtpViaMissedCAllDataRecieved)
            }
    }


    fun onLoginCreateAccountClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT))


    }

    fun onContinueClicked() {


        Log.e("TAG", "onContinueClicked: "+viewState.phoneno )




        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = true
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onSendOTPViaMissedCallClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = false
        viewState.missedCallPopupVisibility = true
        viewState.stillDidntGetOtpPopupVisibility = false

    }

    fun onSendOTPViaMissedCallContinueClicked() {


        onEvent(Event.onSendOTPViaMissedCallContinueClicked)



           onEvent(Event.NavigateTo(Constants.NavigateTo.MISSED_CALL_VERIFY_YOUR_NUMBER))
    }

    fun onGmailLoginClicked() {
        //  onEvent(Event.NavigateTo(Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER))
    }

    fun onSendOTPViaWhatsappClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER))
    }

    fun onSendOTPViaSMSClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.CONFIRM_YOUR_NUMBER))
    }
    fun onSkipButtonClicked() {
    }

    fun onRegisterFormSaveAndContinueClicked() {
    }

    fun onWhatsAppTryAgainClicked() {
    }

    fun onPrivacyPolicyClicked() {
    }

    fun onBackPressed() {
        onEvent(Event.OnBackPressed)
    }

    fun showCountryCodePicker() {
        onEvent(Event.PickerDialog)
    }

    fun onChooseVerificationBackClicked() {
        viewState.loginOrCreateAccountVisibility = true
        viewState.chooseVerificationMethodVisibility = false
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onMissedCallPopupBackClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = true
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onMissedCallPopupOtherMethodClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = true
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
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
        object onSendOTPViaMissedCallContinueClicked : Event()

    }



    private fun setViewPagerListData() {
        val arrayList = arrayListOf<IntroViewPagerItemViewState>()
      /*        val introViewPagerItemViewState1 = IntroViewPagerItemViewState(
            1,
            "ANYWHERE IN THE WORLD!",
            "Enjoy free shipping all across the globe\nonly for you!",
            DrawableViewModel(R.mipmap.iv_onboarding1)
        )
        arrayList.add(introViewPagerItemViewState1)

        val introViewPagerItemViewState2 = IntroViewPagerItemViewState(
            2,
            "PRICE INCLUDES\nPRESCRIPTION LENSES",
            "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState2)

        val introViewPagerItemViewState3 = IntroViewPagerItemViewState(
            3,
            "HOME TRY ON",
            "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState3)

        val introViewPagerItemViewState4 = IntroViewPagerItemViewState(
            4,
            "SAVE MORE BUCKS!",
            "Enjoy our exclusive promos as well as our\nloyalty program to save some more!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState4)

        val introViewPagerItemViewState5 = IntroViewPagerItemViewState(
            5,
            "OFFLINE STORES",
            "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia.",
            DrawableViewModel(R.mipmap.iv_onboarding5)
        )*/
      //  arrayList.add(introViewPagerItemViewState5)
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

}