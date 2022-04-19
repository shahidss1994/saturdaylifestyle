package com.shock.saturdaylifestyle.network

import com.google.gson.JsonObject
import com.shock.saturdaylifestyle.buy.model.ResponseReferralLinkDM
import com.shock.saturdaylifestyle.dashboard.model.*
import com.shock.saturdaylifestyle.leaderboard.models.ResponseLeaderboard
import com.shock.saturdaylifestyle.login_register.models.ResponseAuthDM
import com.shock.saturdaylifestyle.login_register.models.ResponseAvtarProfile
import com.shock.saturdaylifestyle.login_register.models.ResponseCheckUsername
import com.shock.saturdaylifestyle.login_register.models.ResponseUpdateProfile
import retrofit2.http.*


interface ApiCalls {


    @POST("promotion/signUpLoginGoogle")
    suspend fun loginRegister(@Body obj: JsonObject): ResponseAuthDM

    @POST("promotion/checkUserName")
    suspend fun checkUserName(@Body obj: JsonObject): ResponseCheckUsername


    //update current user
    @POST("promotion/updateProfile")
    suspend fun updateAvtarProfile(@Header("token") token:String, @Body obj: JsonObject): ResponseUpdateProfile

    //get current user
    @GET("promotion/publicAvatars")
    suspend fun getAvtarProfile(): ResponseAvtarProfile


    @GET("promotion/getDashboard")
    suspend fun getDashboardApi(@Header("token") token: String): ResponseGetDashboard

    @GET("promotion/avatarCategory")
    suspend fun getAvatarCategory(@Header("token") token: String): ResponseAvatarCategory

    @POST("promotion/avatarsFromCategory")
    suspend fun getAvatarFromCategory(@Body obj: JsonObject): ResponseAvatarFromCategory

    @POST("promotion/stepCount")
    suspend fun updateStepCount(@Header("token") token: String,
    @Body obj: JsonObject): ResponseUpdateStepsDM

    @GET("promotion/redeemReferralPromotionalApp/{userId}")
    suspend fun sendReferredByIdToServer(@Path("userId") userId: String): ResponseUpdateStepsDM


    @POST("promotion/purchaseNFT")
    suspend fun purchaseNFT(@Header("token") token:String, @Body obj: JsonObject): ResponsePurchaseNFT

    @GET("promotion/referralAppLinks")
    suspend fun getreferralLink(@Header("token") token: String): ResponseReferralLinkDM

    @GET("promotion/leaderboard")
    suspend fun leaderboardApi(@Header("token") token:String): ResponseLeaderboard
}