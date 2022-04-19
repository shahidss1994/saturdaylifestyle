package com.shock.saturdaylifestyle.ui.login_register.models

import com.google.gson.annotations.SerializedName

data class ResponseSendOTP(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: Message? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Message(

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Data(

	@field:SerializedName("isUserExist")
	val isUserExist: Boolean? = null,

	@field:SerializedName("token")
	val token: String? = null
)
