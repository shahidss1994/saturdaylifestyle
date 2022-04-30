package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.network.BaseApi
import com.shock.saturdaylifestyle.ui.main.models.CatalogueResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface MainApi  : BaseApi {

    @POST("api/v1/catalogue/")
    suspend fun getCatalogue(
        @Header("x-api-key") key: String,
        @Header("Content-Type") contentType: String
    ): CatalogueResponse

}