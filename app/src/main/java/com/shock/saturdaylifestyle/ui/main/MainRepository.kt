package com.shock.saturdaylifestyle.ui.main

import com.shock.saturdaylifestyle.network.ApiInterface
import com.shock.saturdaylifestyle.network.BaseRepository
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: ApiInterface
) : BaseRepository(api) {

    suspend fun getUsers(page: Int) = safeApiCall { api.getUsers(page) }

    suspend fun registerUser(page: Int) = safeApiCall { api.registerUser(page) }

    suspend fun loginUser(page: Int) = safeApiCall { api.loginUser(page) }

    suspend fun sendOTP(page: Int) = safeApiCall { api.sendOTP(page) }

    suspend fun verifyOTP(page: Int) = safeApiCall { api.verifyOTP(page) }

}