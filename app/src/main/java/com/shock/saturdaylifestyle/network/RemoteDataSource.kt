package com.shock.saturdaylifestyle.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shock.saturdaylifestyle.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {

    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getRetrofitClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getRetrofitClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.header("Accept", "application/json")
                   // runBlocking {  it.header("Authorization", "Bearer ${appDataStore.accessToken.first()}") }
                }.build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                   // client.addInterceptor(logging) //Commented as it was filling log with too much data
                    client.addInterceptor(
                        ChuckerInterceptor.Builder(context)
                            .collector(ChuckerCollector(context))
                            .maxContentLength(250000L)
                            .redactHeaders(emptySet())
                            .alwaysReadResponseBody(true)
                            .build()
                    )
                }
            }
            .cache(null)
            .build()

    }


}