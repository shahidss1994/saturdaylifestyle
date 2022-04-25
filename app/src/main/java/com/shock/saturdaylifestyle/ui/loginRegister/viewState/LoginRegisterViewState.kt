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
    initCountryCodeNumberViewState:CountryCodeNumberViewState = CountryCodeNumberViewState(6, "+62", "Indonesia"),
    initPhoneNoMessageColorViewState: ColorViewState? = null,
    initShowPhoneNoMessage: Boolean = false,
    initContinueBtnDrawableViewState: DrawableViewState = DrawableViewState(R.drawable.bg_button3)
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
    var continueBtnDrawableViewState by bind(
        BR.continueBtnDrawableViewState,
        initContinueBtnDrawableViewState
    )

}