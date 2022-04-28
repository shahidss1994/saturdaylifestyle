package com.shock.saturdaylifestyle.ui.loginRegister.model


data class VerifyOtpModel(
    val data: Data? = null,
    val message: Message? = null,
    val status: Boolean? = false
)

data class Data(
    val isUserExist: Boolean? = false,
    val token: String? = ""
)


