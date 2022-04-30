package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class HeaderViewState(
    initId: String? = null,
    initHeaderItemViewStateList: List<HeaderItemViewState> = arrayListOf()
):BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var headerItemViewStateList by bind(
        BR.headerItemViewStateList,
        initHeaderItemViewStateList
    )
}