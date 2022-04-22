package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind
import com.shock.saturdaylifestyle.ui.base.viewModel.DrawableViewModel

class IntroViewPagerItemViewState(initId:Int? = null, initTitle:String? = null, initDesc:String? = null, initImage: DrawableViewModel? = null): BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var title by bind(BR.title, initTitle)

    @get:Bindable
    var desc by bind(BR.desc, initDesc)

    @get:Bindable
    var image by bind(BR.image, initImage)

}