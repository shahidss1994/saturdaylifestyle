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



    suspend fun login(
        mobile: String,
        otp: String,
        country_code: String,
        device_token: String
    ) =
        safeApiCall { api.login(
            Constants.CONTENT_TYPE,mobile, otp, country_code, device_token,"android") }


    suspend fun sendOtpViaSMS(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOtp(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber,Constants.MSG) }



    suspend fun sendOtpViaWhatsapp(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOtpWhatsapp(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber) }

    suspend fun verifyOTP(key: String,otp: String) =
        safeApiCall { api.verifyOTP(key,Constants.CONTENT_TYPE,otp) }

}