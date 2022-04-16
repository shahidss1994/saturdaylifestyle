package com.shock.saturdaylifestyle.network

import com.shock.saturdaylifestyle.ui.login_register.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface : BaseApi {

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


    @POST("otp/send")
    suspend fun sendOTP(@Query("page") page: Int): Any

    @GET("otp/verify")
    suspend fun verifyOTP(@Query("page") page: Int): Any
}