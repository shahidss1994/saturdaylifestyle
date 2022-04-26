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
    var continueBtnDrawableViewState by bind(
        BR.continueBtnDrawableViewState,
        initContinueBtnDrawableViewState
    )

}