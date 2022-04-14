package com.shock.saturdaylifestyle.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface : BaseApi {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any


    @POST("user/register")
    suspend fun registerUser(@Query("page") page: Int): Any


    @POST("user/login")
    suspend fun loginUser(@Query("page") page: Int): Any


    @POST("otp/send")
    suspend fun sendOTP(@Query("page") page: Int): Any

    @GET("otp/verify")
    suspend fun verifyOTP(@Query("page") page: Int): Any
}