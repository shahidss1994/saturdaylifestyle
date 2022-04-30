package com.shock.saturdaylifestyle.ui.main.network

import com.shock.saturdaylifestyle.network.BaseApi
import com.shock.saturdaylifestyle.ui.main.models.CatalogueFilterRequest
import com.shock.saturdaylifestyle.ui.main.models.CatalogueResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface MainApi : BaseApi {

    @GET("api/v/catalogue/")
    suspend fun getCatalogue(
        @Header("x-api-key") key: String,
        @Header("Content-Type") contentType: String,
        @Body filterBody: CatalogueFilterRequest?
    ): CatalogueResponse

}