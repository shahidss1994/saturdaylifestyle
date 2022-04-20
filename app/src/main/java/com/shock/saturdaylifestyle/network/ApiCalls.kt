package com.shock.saturdaylifestyle.network

import com.shock.saturdaylifestyle.ui.login_register.entity.RegisterEntity
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseSendOTP
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*


interface ApiCalls {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any


    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("mobile") phoneNumber: String,
        @Field("country_code") countryCode: String,
        @Field("gender") genderType: Int,
        @Field("referral_code") referral: String
    ): Flow<RegisterEntity>


    @POST("user/login")
    suspend fun loginUser(@Query("page") page: Int): Any

    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOTP(@Header("x-api-key") key: String,
                        @Header("Content-Type") type: String,
                        @Field("country_code") countryCode: String,
                        @Field("number") number: String): ResponseSendOTP

    @FormUrlEncoded
    @POST("otp/verify")
    suspend fun verifyOTP(@Header("x-api-key") key: String,
                          @Header("Content-Type") type: String,
                          @Field("country_code") countryCode: String): ResponseSendOTP



}