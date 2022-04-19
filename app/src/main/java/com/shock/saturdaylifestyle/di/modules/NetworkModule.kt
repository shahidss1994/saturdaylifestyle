package com.shock.saturdaylifestyle.di.modules

import android.content.Context

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    private var okHttpClient: OkHttpClient? = null
    private val context: Context? = null

    @Provides
    fun bindNetworkProvider(networkProvider: NetworkProviderImpl): NetworkProvider {
        return networkProvider
    }

    @Provides
    fun bindNetworkConfiguration(networkConfiguration: NetworkConfigurationImpl): NetworkConfiguration {
        return networkConfiguration
    }

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//    val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
//    // 15 MiB cache
//    val cache = Cache(cacheDir, 15 * 1024 * 1024)
        return OkHttpClient.Builder()
//      .cache(
//        cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(80, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()


    @Provides
    @Reusable
    internal fun provideApiCalls(retrofit: Retrofit): ApiCalls =
        retrofit.create(ApiCalls::class.java)


}