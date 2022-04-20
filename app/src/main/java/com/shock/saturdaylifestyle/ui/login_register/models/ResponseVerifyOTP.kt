package com.shock.saturdaylifestyle.ui.login_register.models

import com.google.gson.annotations.SerializedName

data class ResponseVerifyOTP(

	/*@field:SerializedName("data")
	val data: Data? = null,*/

	@field:SerializedName("message")
	val message: Message? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

