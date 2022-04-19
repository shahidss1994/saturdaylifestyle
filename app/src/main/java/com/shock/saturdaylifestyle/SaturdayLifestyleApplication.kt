package com.shock.saturdaylifestyle

import android.app.Application
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.di.component.AppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SaturdayLifestyleApplication : Application() {



    private lateinit var mAppComponent: AppComponent

    init {
        instance_ = this
    }

    companion object {
        lateinit var instance_: SaturdayLifestyleApplication

        fun getInstance() = instance_
    }

    override fun onCreate() {
        super.onCreate()

        DaggerProvider.initComponent(this)
        DaggerProvider.getAppComponent()?.inject(this)
       // FirebaseApp.initializeApp(this)


    }
}