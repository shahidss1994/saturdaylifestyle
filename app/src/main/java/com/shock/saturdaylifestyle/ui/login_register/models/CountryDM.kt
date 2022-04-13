package com.saturdays.login_register.models

data class CountryDM(
    var id: Int? = null,
    var code: String? = "",
    var countryName: String? = "",
    var isSelected: Boolean? = false
)