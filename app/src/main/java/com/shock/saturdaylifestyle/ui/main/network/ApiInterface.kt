package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.network.BaseApi
import com.shock.saturdaylifestyle.ui.loginRegister.model.SendOtpModel
import retrofit2.http.*

interface MainApi : BaseApi {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any


    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOTPMissCall(@Header("x-api-key") key: String,
                                @Header("Content-Type") contentType: String,
                                @Field("country_code") countryCode: String,
                                @Field("number") number: String,
                                @Field("type") type: String): SendOtpModel


    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOtpSms(@Header("x-api-key") key: String,
                                @Header("Content-Type") contentType: String,
                                @Field("country_code") countryCode: String,
                                @Field("number") number: String,
                                @Field("type") type: String): SendOtpModel




}