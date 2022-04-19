package com.shock.saturdaylifestyle.di.modules



import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shock.saturdaylifestyle.SaturdayLifestyleApplication
import com.shock.saturdaylifestyle.errorProvider.ErrorProvider
import com.shock.saturdaylifestyle.errorProvider.ErrorProviderImpl
import com.shock.saturdaylifestyle.util.PrefUtils
import com.shock.saturdaylifestyle.util.Util


import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: SaturdayLifestyleApplication) {

    // region Global Use
    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideErrorProvider(): ErrorProvider {
        return ErrorProviderImpl(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideUtil(): Util = Util(application.applicationContext)

    @Singleton
    @Provides
    fun PrefUtils(): PrefUtils = PrefUtils(application.applicationContext)




}