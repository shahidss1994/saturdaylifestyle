package com.shock.saturdaylifestyle.constants

object Constants {

    const val Saturdays = "Saturdays"
    const val BASE_URL = "https://api.weeknds.com/api/v1/"
    const val API_KEY = "GCMUDiuY5a7WvyUNt9n3QztToSHzK7Uj"
    val TOKEN = "token"
    val IS_LOGIN = "is_login"
    val DEVICE_TOKEN = "device_token"
    val IS_FIRST_TIME = "is_first_time"
    val NAME = "name"
    val EMAIL = "email"
    val OTP = "otp"
    val FROM_WHICH_SCREEN = "from_which_screen"

    object NavigateTo {
        const val LOGIN_OR_CREATE_ACCOUNT = "login_or_create_account"
        const val WHATSAPP_VERIFY_YOUR_NUMBER = "whatsapp_verify_your_number"
        const val CONFIRM_YOUR_NUMBER = "confirm_your_number"
        const val REGISTER_FORM = "register_form"
    }

    object TextWatcherType{
        const val PHONE_NO = "phone_no"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val COUNTRY_CODE = "country_code"
    }

    object Gender{
        const val MALE = "male"
        const val FEMALE = "female"
    }

}