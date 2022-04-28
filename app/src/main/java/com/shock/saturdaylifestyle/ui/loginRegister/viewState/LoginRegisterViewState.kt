package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.ui.base.others.bind
import com.shock.saturdaylifestyle.ui.base.viewState.ColorViewState
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState

class LoginRegisterViewState(
    initIntroViewPagerItemViewStateList: List<IntroViewPagerItemViewState> = arrayListOf(),
    initCountryCodeNumberViewStateList: List<CountryCodeNumberViewState> = arrayListOf(),
    initLoginOrCreateAccountVisibility: Boolean = true,
    initSendOtpSmsTryAgainVisibility: Boolean = true,
    initCountryCodeNumberViewState: CountryCodeNumberViewState = CountryCodeNumberViewState(
        "id",
        "62",
        "Indonesia"
    ),
    initPhoneNoMessageColorViewState: ColorViewState? = null,
    initSmsOtpColorViewState: ColorViewState? = null,
    initShowPhoneNoMessage: Boolean = false,
    initShowSmsOtpError: Boolean = false,
    initChooseVerificationMethodVisibility: Boolean = false,
    initMissedCallPopupVisibility: Boolean = false,
    initStillDidntGetOtpPopupVisibility: Boolean = false,
    initContinueBtnDrawableViewState: DrawableViewState = DrawableViewState(R.drawable.bg_button3),
    initPhoneNo: String = "",
    initPhoneNoStillNoOtp: String = "",
    initSmsTryAgainTimerText: String = "30",
    initSendOtpSmsTryAgainClickCount: Int = 0,
    initRegisterFlow: Int = 0

) :
    BaseObservable() {

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
    var countryCodeNumberViewState by bind(
        BR.countryCodeNumberViewState,
        initCountryCodeNumberViewState
    )

    @get:Bindable
    var phoneNoMessageColorViewState by bind(
        BR.phoneNoMessageColorViewState,
        initPhoneNoMessageColorViewState
    )

    @get:Bindable
    var showPhoneNoMessage by bind(
        BR.showPhoneNoMessage,
        initShowPhoneNoMessage
    )

    @get:Bindable
    var showSmsOtpError by bind(
        BR.showSmsOtpError,
        initShowSmsOtpError
    )

/*    @get:Bindable
    var smsOtpColorViewState by bind(
        BR.smsOtpColorViewState,
        initSmsOtpColorViewState
    )*/


 /*   @get:Bindable
    var smsOtp by bind(
        BR.smsOtp,
        initSmsOtp
    )*/


    @get:Bindable
    var registerFlow by bind(
        BR.registerFlow,
        initRegisterFlow
    )


    @get:Bindable
    var continueBtnDrawableViewState by bind(
        BR.continueBtnDrawableViewState,
        initContinueBtnDrawableViewState
    )

    @get:Bindable
    var phoneNo by bind(
        BR.phoneNo,
        initPhoneNo
    )


    @get:Bindable
    var phoneNoStillNoOtp by bind(
        BR.phoneNoStillNoOtp,
        initPhoneNo
    )

    @get:Bindable
    var smsTryAgainTimerText by bind(
        BR.smsTryAgainTimerText,
        initSmsTryAgainTimerText
    )

    @get:Bindable
    var sendOtpSmsTryAgainClickCount by bind(
        BR.sendOtpSmsTryAgainClickCount,
        initSendOtpSmsTryAgainClickCount
    )


    @get:Bindable
    var sendOtpSmsTryAgainVisibility by bind(
        BR.sendOtpSmsTryAgainVisibility,
        initSendOtpSmsTryAgainVisibility
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

}