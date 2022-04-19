package com.tr.di.modules.ViewModules

import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.buy.viewmodel.NFTBuyStatusVM
import com.shock.saturdaylifestyle.dashboard.view_model.DashboardViewModel
import com.shock.saturdaylifestyle.leaderboard.view_model.LeaderboardViewModel
import com.shock.saturdaylifestyle.login_register.viewmodel.LoginRegisterVM
import com.shock.saturdaylifestyle.login_register.viewmodel.ProfileSetupVM
import com.shock.saturdaylifestyle.splash.viewmodel.SplashViewModel
import com.shock.saturdaylifestyle.util.ViewModelKey


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterVM::class)
    abstract fun bindLoginRegisterVM(loginRegisterVM: LoginRegisterVM): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ProfileSetupVM::class)
    abstract fun bindProfileSetupVM(profileSetupVM: ProfileSetupVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(loginRegisterVM: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NFTBuyStatusVM::class)
    abstract fun bindNFTBuyStatusVM(myNFTBuyStatusVM: NFTBuyStatusVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeaderboardViewModel::class)
    abstract fun bindLeaderboardViewModel(leaderboardViewModel: LeaderboardViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}