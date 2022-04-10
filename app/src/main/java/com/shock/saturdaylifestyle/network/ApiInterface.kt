package com.shock.saturdaylifestyle.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface : BaseApi {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any

}