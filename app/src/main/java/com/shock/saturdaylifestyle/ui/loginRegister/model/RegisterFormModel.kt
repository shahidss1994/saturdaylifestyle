package com.shock.saturdaylifestyle.ui.loginRegister.model

import com.shock.saturdaylifestyle.constants.Constants

data class RegisterFormModel(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var dob: String = "",
    var otp: String = "",
    var gender: Int = Constants.Gender.MALE
)