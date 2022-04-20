package com.shock.saturdaylifestyle.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRegisterData @Inject constructor(private val api: ApiCalls) {

    suspend fun SendOtpProcess(key: String, contentType: String, phoneNumber: String, countryCode: String) = withContext(Dispatchers.IO) {
        val data = api.sendOTP(key,contentType,countryCode,phoneNumber)
        data
    }

    suspend fun VerifyOtpProcess(key: String, contentType: String, otp: String) = withContext(Dispatchers.IO) {
        val data = api.verifyOTP(key,contentType,otp)
        data
    }

}












