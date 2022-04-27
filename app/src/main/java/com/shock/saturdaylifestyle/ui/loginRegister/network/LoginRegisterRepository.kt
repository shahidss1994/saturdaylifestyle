package com.shock.saturdaylifestyle.ui.loginRegister.network

import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.BaseRepository
import com.shock.saturdaylifestyle.ui.main.network.MainApi
import javax.inject.Inject

class LoginRegisterRepository @Inject constructor(
    private val api: MainApi
) : BaseRepository(api) {

    suspend fun sendOtpViaMissedCall(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOTPMissCall(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber,Constants.CITCALL) }

    suspend fun sendOtpViaSMS(key: String, phoneNumber: String, countryCode: String) =
        safeApiCall { api.sendOtpSms(key,
            Constants.CONTENT_TYPE,countryCode,phoneNumber,Constants.MSG) }

}