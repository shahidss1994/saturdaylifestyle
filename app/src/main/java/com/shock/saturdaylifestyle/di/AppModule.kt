package com.shock.saturdaylifestyle.di

import android.content.Context
import com.shock.saturdaylifestyle.network.RemoteDataSource
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterApi
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
    ) : LoginRegisterApi {
        return remoteDataSource.buildApi(LoginRegisterApi::class.java, context)
    }

}