package com.shock.saturdaylifestyle.ui.loginRegister.model


data class SendOtpModel(
    val message: Message? = null,
    val status: Boolean? = false
)

data class Message(
    val en: String? = "",
    val id: String? = ""
)
