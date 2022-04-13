package com.shock.saturdaylifestyle.ui.splash

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.common.bind

class SplashViewState(initWelcomeMsg: String? = null):BaseObservable() {

    @get:Bindable
    var welcomeMsg by bind(BR.welcomeMsg, initWelcomeMsg)

}