package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState
import com.shock.saturdaylifestyle.ui.base.viewState.StringViewState

class DetailsMakeUsDifferentItemViewState(
    initId: Int = -1,
    initImageViewState: DrawableViewState? = null,
    initTitle: StringViewState? = null,
    initDescription: StringViewState? = null
) : BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var imageViewState by bind(BR.imageViewState, initImageViewState)

    @get:Bindable
    var title by bind(BR.title, initTitle)

    @get:Bindable
    var description by bind(BR.description, initDescription)

}