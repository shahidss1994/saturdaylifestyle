package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.network.BaseApi
import com.shock.saturdaylifestyle.ui.loginRegister.model.SendOtpModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MainApi : BaseApi {

    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Any

    @FormUrlEncoded
    @POST("user/otp/send")
    suspend fun sendOtp(@Header("x-api-key") key: String,
                                @Header("Content-Type") contentType: String,
                                @Field("country_code") countryCode: String,
                                @Field("number") number: String,
                                @Field("type") type: String): SendOtpModel

}