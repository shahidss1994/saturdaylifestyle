package com.shock.saturdaylifestyle.ui.loginRegister.viewState

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shock.saturdaylifestyle.BR
import com.shock.saturdaylifestyle.ui.base.others.bind

class CountryCodeNumberViewState(
    initId: Int? = null,
    initCode: String? = "",
    initName: String? = ""
) : BaseObservable() {

    @get:Bindable
    var id by bind(BR.id, initId)

    @get:Bindable
    var code by bind(BR.code, initCode)

    @get:Bindable
    var name by bind(BR.name, initName)

}

