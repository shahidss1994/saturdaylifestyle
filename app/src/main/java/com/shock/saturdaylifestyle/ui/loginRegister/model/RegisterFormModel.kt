package com.shock.saturdaylifestyle.ui.loginRegister.model

import com.shock.saturdaylifestyle.constants.Constants

data class RegisterFormModel(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var dob: String = "",
    var gender: String = Constants.Gender.MALE
){
    fun clear(){
        firstName = ""
        lastName = ""
        email = ""
        dob = ""
        gender = Constants.Gender.MALE
    }
}