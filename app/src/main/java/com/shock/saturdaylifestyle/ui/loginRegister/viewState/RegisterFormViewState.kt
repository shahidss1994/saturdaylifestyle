package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.ui.base.others.bind
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState

class RegisterFormViewState(
    initShowFirstNameError: Boolean = false,
    initShowLastNameError: Boolean = false,
    initShowEmailError: Boolean = false,
    initShowBirthdayError: Boolean = false,
    initShowMobileNoError: Boolean = false,
    initPhoneNumberViewState: CountryCodeNumberViewState = CountryCodeNumberViewState("id", "62", "Indonesia"),
    initFreezePhoneNo: Boolean = false,
    initEmail: String = "",
    initFreezeEmail: Boolean = false,
    initContinueBtnDrawableViewState: DrawableViewState = DrawableViewState(R.drawable.bg_button3),
) : BaseObservable() {

    @get:Bindable
    var showFirstNameError by bind(
        BR.showFirstNameError,
        initShowFirstNameError
    )

    @get:Bindable
    var showLastNameError by bind(
        BR.showLastNameError,
        initShowLastNameError
    )

    @get:Bindable
    var showEmailError by bind(
        BR.showEmailError,
        initShowEmailError
    )

    @get:Bindable
    var showBirthdayError by bind(
        BR.showBirthdayError,
        initShowBirthdayError
    )

    @get:Bindable
    var showMobileNoError by bind(
        BR.showMobileNoError,
        initShowMobileNoError
    )

    @get:Bindable
    var phoneNumberViewState by bind(
        BR.phoneNumberViewState,
        initPhoneNumberViewState
    )

    @get:Bindable
    var email by bind(
        BR.email,
        initEmail
    )

    @get:Bindable
    var continueBtnDrawableViewState by bind(
        BR.continueBtnDrawableViewState,
        initContinueBtnDrawableViewState
    )

    @get:Bindable
    var freezePhoneNo by bind(
        BR.freezePhoneNo,
        initFreezePhoneNo
    )

    @get:Bindable
    var freezeEmail by bind(
        BR.freezeEmail,
        initFreezeEmail
    )

}