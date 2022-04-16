package com.shock.saturdaylifestyle.ui.login_register.entity


/*
* {
    "success": false,
    "message": "Auth Failed",
    "messageTitle": null,
    "data": null,
    "responseTime": "0 ms."
}
* */
data class RegisterEntity (
    val success : Boolean,
    val message : String,
    val messageTitle : String,
    val data : String,
    val responseTime : String,
)