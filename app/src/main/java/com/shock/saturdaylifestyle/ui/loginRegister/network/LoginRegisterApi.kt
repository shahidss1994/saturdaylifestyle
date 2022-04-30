package com.shock.saturdaylifestyle.ui.loginRegister.network

import com.shock.saturdaylifestyle.network.BaseApi
import com.shock.saturdaylifestyle.ui.loginRegister.model.LoginRegisterResponseModel
import com.shock.saturdaylifestyle.ui.loginRegister.model.SendOtpModel
import com.shock.saturdaylifestyle.ui.loginRegister.model.VerifyOtpModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginRegisterApi : BaseApi {

    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOtp(
        @Header("x-api-key") key: String,
        @Header("Content-Type") contentType: String,
        @Field("country_code") countryCode: String,
        @Field("number") number: String,
        @Field("type") type: String
    ): SendOtpModel



    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOtpWhatsapp(
        @Header("x-api-key") key: String,
        @Header("Content-Type") contentType: String,
        @Field("country_code") countryCode: String,
        @Field("number") number: String
    ): SendOtpModel



    @FormUrlEncoded
    @POST("user/otp/verify")
    suspend fun verifyOTP(@Header("x-api-key") key: String,
                          @Header("Content-Type") type: String,
                          @Field("otp") countryCode: String): VerifyOtpModel


    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Header("Content-Type") type: String,
        @Field("name") name: String,
        @Field("mobile") phoneNumber: String,
        @Field("country_code") countryCode: String,
        @Field("gender") genderType: Int,
        @Field("email") email: String
        //   @Field("referral_code") referral: String
    ): LoginRegisterResponseModel



}