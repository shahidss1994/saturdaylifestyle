package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class LoginRegisterViewState(
    initIntroViewPagerItemViewStateList: List<IntroViewPagerItemViewState> = arrayListOf(),
    initLoginOrCreateAccountVisibility: Boolean = true
) :
    BaseObservable() {

    @get:Bindable
    var introViewPagerItemViewStateList by bind(
        BR.introViewPagerItemViewStateList,
        initIntroViewPagerItemViewStateList
    )

    @get:Bindable
    var loginOrCreateAccountVisibility by bind(
        BR.loginOrCreateAccountVisibility,
        initLoginOrCreateAccountVisibility
    )


}