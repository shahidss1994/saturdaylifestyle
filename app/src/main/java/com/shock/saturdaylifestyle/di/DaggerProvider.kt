package com.shock.saturdaylifestyle.di

import com.shock.saturdaylifestyle.SaturdayLifestyleApplication
import com.shock.saturdaylifestyle.di.component.AppComponent
//import com.shock.saturdaylifestyle.di.component.DaggerAppComponent
import com.shock.saturdaylifestyle.di.modules.ApplicationModule



class DaggerProvider {

    companion object {
        private var appComponent: AppComponent? = null

        fun initComponent(application: SaturdayLifestyleApplication) {
            appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
        }

        fun getAppComponent(): AppComponent? {
            return appComponent
        }

    }
}