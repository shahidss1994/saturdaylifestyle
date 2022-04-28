package com.shock.saturdaylifestyle.ui.loginRegister.model

import com.google.gson.annotations.SerializedName

data class LoginRegisterResponseModel(

	@field:SerializedName("data")
	val data: UserData? = null,

	@field:SerializedName("message")
	val message: String? = null,

/*	@field:SerializedName("message")
	val languageMessage: Message? = null,*/

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class UserData(

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("is_send_newsletter")
	val isSendNewsletter: Boolean? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("is_send_email")
	val isSendEmail: Boolean? = null,

	@field:SerializedName("currency_code")
	val currencyCode: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("is_first_order")
	val isFirstOrder: Int? = null,

	@field:SerializedName("dob")
	val dob: String? = null,

	@field:SerializedName("referral_code")
	val referralCode: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("is_send_notifications")
	val isSendNotifications: Boolean? = null,

	@field:SerializedName("profile_image_base_url")
	val profileImageBaseUrl: String? = null,

	@field:SerializedName("referral_amount")
	val referralAmount: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("country_calling_code")
	val countryCallingCode: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
