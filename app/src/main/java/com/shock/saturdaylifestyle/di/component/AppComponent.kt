package com.shock.saturdaylifestyle.di.component

import com.shock.saturdaylifestyle.SaturdayLifestyleApplication
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.ui.login_register.view.OTPActivity
import com.shock.saturdaylifestyle.ui.login_register.view.RegisterForm1Activity
import com.shock.saturdaylifestyle.ui.splash.SplashActivity
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelModule
import com.shock.saturdaylifestyle.di.modules.ApplicationModule
import com.shock.saturdaylifestyle.di.modules.NetworkModule
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelFactoryModule
import com.shock.saturdaylifestyle.ui.on_boarding.view.OnboardingActivity
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
    fun inject(loginRegisterActivity: LoginRegisterActivity)
    fun inject(registerForm1Activity: RegisterForm1Activity)
    fun inject(oTPActivity: OTPActivity)
    fun inject(onboardingActivity: OnboardingActivity)


}
