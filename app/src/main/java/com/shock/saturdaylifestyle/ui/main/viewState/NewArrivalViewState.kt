package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class NewArrivalViewState(
    initId: String? = null,
    initNewArrivalItemViewStateList: List<NewArrivalItemViewState> = arrayListOf()
):BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var newArrivalItemViewStateList by bind(
        BR.newArrivalItemViewStateList,
        initNewArrivalItemViewStateList
    )

}