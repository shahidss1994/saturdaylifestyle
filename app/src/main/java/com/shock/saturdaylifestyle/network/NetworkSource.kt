package com.shock.saturdaylifestyle.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRegisterData @Inject constructor(private val api: ApiCalls) {

    suspend fun SendOtpWhatsappProcess(key: String, contentType: String, phoneNumber: String, countryCode: String) = withContext(Dispatchers.IO) {
        val data = api.sendOTP(key,contentType,countryCode,phoneNumber)
        data
    }

    suspend fun SendOtpProcess(key: String, contentType: String, phoneNumber: String, countryCode: String) = withContext(Dispatchers.IO) {
        val data = api.sendOTPWhatsapp(key,contentType,countryCode,phoneNumber,"MSG")
        data
    }


    suspend fun SendOtpMissCallProcess(key: String, contentType: String, phoneNumber: String, countryCode: String) = withContext(Dispatchers.IO) {
        val data = api.sendOTPMissCall(key,contentType,countryCode,phoneNumber,"CITCALL")
        data
    }


    suspend fun VerifyOtpProcess(key: String, contentType: String, otp: String) = withContext(Dispatchers.IO) {
        val data = api.verifyOTP(key,contentType,otp)
        data
    }

    suspend fun LoginProcess( contentType: String,
                              mobile: String,
                              otp: String,
                              country_code: String,
                              device_token: String,
                              device_type: String
    ) = withContext(Dispatchers.IO) {
        val data = api.loginUser(contentType,mobile,otp,country_code, device_token, device_type)
        data
    }

    suspend fun RegisterProcess(contentType: String,
                              name: String,
                              mobile: String,
                              country_code: String,
                              genderType: Int,
                              email: String
    ) = withContext(Dispatchers.IO) {
        val data = api.registerUser(contentType,name,mobile,country_code, genderType, email)
        data
    }



}












