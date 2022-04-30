package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class DetailsMakeUsDifferentViewState(
    initId: String? = null,
    initDetailsMakesUsDifferentItemViewStateList: List<DetailsMakeUsDifferentItemViewState> = arrayListOf()
):BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var detailsMakesUsDifferentItemViewStateList by bind(
        BR.detailsMakesUsDifferentItemViewStateList,
        initDetailsMakesUsDifferentItemViewStateList
    )

}