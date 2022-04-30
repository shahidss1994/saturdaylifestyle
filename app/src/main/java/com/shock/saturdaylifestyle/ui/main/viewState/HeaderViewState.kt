package com.shock.saturdaylifestyle.ui.main.viewState

import android.os.Handler
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class HeaderViewState(
    initId: String? = null,
    initHeaderItemViewStateList: List<HeaderItemViewState> = arrayListOf(),
    initPreviousPosition: Int = 0,
    initRunnable: Runnable? = null,
    initHandler: Handler? = null
) : BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var headerItemViewStateList by bind(
        BR.headerItemViewStateList,
        initHeaderItemViewStateList
    )

    @get:Bindable
    var previousPosition by bind(BR.previousPosition, initPreviousPosition)

    @get:Bindable
    var runnable by bind(BR.runnable, initRunnable)

    @get:Bindable
    var handler by bind(BR.handler, initHandler)

}