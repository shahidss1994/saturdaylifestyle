package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class LoginRegisterViewState(
    initIntroViewPagerItemViewStateList: List<IntroViewPagerItemViewState> = arrayListOf(),
    initCountryCodeNumberViewStateList: List<CountryCodeNumberViewState> = arrayListOf(),
    initLoginOrCreateAccountVisibility: Boolean = true,
    initChooseVerificationMethodVisibility: Boolean = false,
    initMissedCallPopupVisibility: Boolean = false,
    initStillDidntGetOtpPopupVisibility: Boolean = false,
    initPhoneNo: String? = null,
    initCountryCodeNumberViewState:CountryCodeNumberViewState = CountryCodeNumberViewState(6, "+62", "Indonesia")
) :
    BaseObservable() {

    @get:Bindable
    var phoneno by bind(
        BR.phoneno,
        initPhoneNo
    )

    @get:Bindable
    var phonenoForBottomSheet by bind(
        BR.phonenoForBottomSheet,
        initPhoneNo
    )

    @get:Bindable
    var introViewPagerItemViewStateList by bind(
        BR.introViewPagerItemViewStateList,
        initIntroViewPagerItemViewStateList
    )

    @get:Bindable
    var countryCodeNumberViewStateList by bind(
        BR.countryCodeNumberViewStateList,
        initCountryCodeNumberViewStateList
    )

    @get:Bindable
    var loginOrCreateAccountVisibility by bind(
        BR.loginOrCreateAccountVisibility,
        initLoginOrCreateAccountVisibility
    )


    @get:Bindable
    var chooseVerificationMethodVisibility by bind(
        BR.chooseVerificationMethodVisibility,
        initChooseVerificationMethodVisibility
    )

    @get:Bindable
    var stillDidntGetOtpPopupVisibility by bind(
        BR.stillDidntGetOtpPopupVisibility,
        initStillDidntGetOtpPopupVisibility
    )

    @get:Bindable
    var missedCallPopupVisibility by bind(
        BR.missedCallPopupVisibility,
        initMissedCallPopupVisibility
    )

    @get:Bindable
    var countryCodeNumberViewState by bind(
        BR.countryCodeNumberViewState,
        initCountryCodeNumberViewState
    )

}