package com.shock.saturdaylifestyle.di.component




import com.shock.saturdaylifestyle.SaturdayLifestyleApplication
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.ui.login_register.view.OTPActivity
import com.shock.saturdaylifestyle.ui.login_register.view.RegisterForm1Activity
import com.shock.saturdaylifestyle.ui.splash.SplashActivity
import com.tr.di.modules.ViewModules.ViewModelModule
import com.shock.saturdaylifestyle.di.modules.ApplicationModule
import com.shock.saturdaylifestyle.di.modules.NetworkModule
import com.tribU.di.modules.ViewModules.ViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)

interface AppComponent {

    fun inject(myApplication: SaturdayLifestyleApplication)
    fun inject(splashActivity: SplashActivity)
    fun inject(splashActivity: LoginRegisterActivity)
    fun inject(splashActivity: RegisterForm1Activity)
    fun inject(splashActivity: OTPActivity)


}


/*
top - max coin - 700
max-700
* max - according to */