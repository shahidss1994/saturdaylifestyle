package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class LoginRegisterViewState(
    initIntroViewPagerItemViewStateList: List<IntroViewPagerItemViewState> = arrayListOf(),
    initCountryCodeNumberViewStateList: List<CountryCodeNumberViewState> = arrayListOf(),
    initLoginOrCreateAccountVisibility: Boolean = true,
    initCountryCodeNumberViewState:CountryCodeNumberViewState = CountryCodeNumberViewState(6, "+62", "Indonesia")
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

}