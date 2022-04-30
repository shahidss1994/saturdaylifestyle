package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class ExploreOurTopPicksViewState(
    initId: String? = null,
    initExploreTopPicksItemViewStateList: List<ExploreTopPicksItemViewState> = arrayListOf()
):BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var initExploreTopPicksItemViewStateList by bind(
        BR.rvExploreTopPickSublist,
        initExploreTopPicksItemViewStateList
    )

}