package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.network.BaseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi : BaseApi {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any

}