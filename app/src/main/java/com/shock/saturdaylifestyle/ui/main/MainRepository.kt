package com.shock.saturdaylifestyle.ui.main

import com.shock.saturdaylifestyle.network.ApiInterface
import com.shock.saturdaylifestyle.network.BaseRepository
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: ApiInterface
) : BaseRepository(api) {

    suspend fun getUsers(page: Int) = safeApiCall { api.getUsers(page) }

}