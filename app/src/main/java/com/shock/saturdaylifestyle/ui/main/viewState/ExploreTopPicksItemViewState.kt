package com.shock.saturdaylifestyle.ui.main.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.ui.base.others.bind

class ExploreTopPicksItemViewState (initId: Int = -1,
                                    initViewImage: String? = null,
                                    initTitle: String = Constants.SectionName.EMPTY
) : BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var viewState by bind(BR.viewState, initViewImage)

    @get:Bindable
    var title by bind(BR.sectionName, initTitle)

}