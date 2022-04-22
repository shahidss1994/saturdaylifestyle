package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class MainViewState(initWelcomeMsg: String? = null):BaseObservable() {

    @get:Bindable
    var welcomeMsg by bind(BR.welcomeMsg, initWelcomeMsg)

}
