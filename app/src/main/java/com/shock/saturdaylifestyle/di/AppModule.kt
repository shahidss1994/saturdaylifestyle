package com.shock.saturdaylifestyle.di

import android.content.Context
import com.shock.saturdaylifestyle.network.ApiInterface
import com.shock.saturdaylifestyle.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(
        @ApplicationContext context: Context,
        remoteDataSource: RemoteDataSource
    ) : ApiInterface {
        return remoteDataSource.buildApi(ApiInterface::class.java, context)
    }

}