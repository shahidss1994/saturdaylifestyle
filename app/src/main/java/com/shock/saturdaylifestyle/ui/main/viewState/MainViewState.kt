package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class MainViewState(initHomeItemList: List<HomeViewState> = arrayListOf()) : BaseObservable() {

    @get:Bindable
    var homeItemList by bind(BR.homeItemList, initHomeItemList)

}