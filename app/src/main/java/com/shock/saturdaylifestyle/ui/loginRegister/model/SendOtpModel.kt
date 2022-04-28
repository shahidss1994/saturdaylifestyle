package com.shock.saturdaylifestyle.ui.loginRegister.model


data class SendOtpModel(
    val data: Data? = null,
    val message: Message? = null,
    val status: Boolean? = false
)

data class Message(
    val en: String? = "",
    val id: String? = ""
)

data class Data(
    val isUserExist: Boolean? = false,
    val token: String? = ""
)
