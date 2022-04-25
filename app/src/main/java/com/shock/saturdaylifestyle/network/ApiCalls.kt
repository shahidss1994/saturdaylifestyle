package com.shock.saturdaylifestyle.network

import com.shock.saturdaylifestyle.ui.login_register.models.ResponseLoginRegisterUser
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseSendOTP
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseVerifyOTP
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*


interface ApiCalls {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any


    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerUser(
        @Header("Content-Type") type: String,
        @Field("name") name: String,
        @Field("mobile") phoneNumber: String,
        @Field("country_code") countryCode: String,
        @Field("gender") genderType: Int,
        @Field("email") email: String
     //   @Field("referral_code") referral: String
    ): ResponseLoginRegisterUser

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginUser(
        @Header("Content-Type") type: String,
        @Field("mobile") mobile: String,
        @Field("otp") otp: String,
        @Field("country_code") country_code: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: String
    ): ResponseLoginRegisterUser

    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOTP(@Header("x-api-key") key: String,
                        @Header("Content-Type") type: String,
                        @Field("country_code") countryCode: String,
                        @Field("number") number: String): ResponseSendOTP


    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOTPWhatsapp(@Header("x-api-key") key: String,
                        @Header("Content-Type") contentType: String,
                        @Field("country_code") countryCode: String,
                        @Field("number") number: String,
                        @Field("type") type: String): ResponseSendOTP



    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOTPMissCall(@Header("x-api-key") key: String,
                                @Header("Content-Type") contentType: String,
                                @Field("country_code") countryCode: String,
                                @Field("number") number: String,
                                @Field("type") type: String): ResponseSendOTP




    @FormUrlEncoded
    @POST("user/otp/verify")
    suspend fun verifyOTP(@Header("x-api-key") key: String,
                          @Header("Content-Type") type: String,
                          @Field("otp") countryCode: String): ResponseVerifyOTP



}