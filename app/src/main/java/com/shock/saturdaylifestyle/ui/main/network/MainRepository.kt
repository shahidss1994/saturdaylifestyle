package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.BaseRepository
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: MainApi
) : BaseRepository(api) {

    suspend fun getCatalogue(key: String) =
        safeApiCall { api.getCatalogue(key, Constants.CONTENT_TYPE) }

}