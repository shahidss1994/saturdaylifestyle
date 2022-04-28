package com.shock.saturdaylifestyle.ui.loginRegister.network

import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.BaseRepository
import javax.inject.Inject

class LoginRegisterRepository @Inject constructor(
    private val api: LoginRegisterApi
) : BaseRepository(api) {

    suspend fun sendOtpViaMissedCall(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOtp(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber,Constants.CITCALL) }


    suspend fun register(
        name: String,
        phoneNo: String,
        code: String,
        gender: Int,
        email: String
    ) =
        safeApiCall { api.register(
            Constants.CONTENT_TYPE,name,phoneNo,code,gender,email) }


    suspend fun sendOtpViaSMS(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOtp(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber,Constants.MSG) }

    suspend fun verifyOTP(key: String,otp: String) =
        safeApiCall { api.verifyOTP(key,Constants.CONTENT_TYPE,otp) }

}