package com.shock.saturdaylifestyle.di.modules.ViewModules

import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.ui.login_register.viewmodel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.util.ViewModelKey



import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {



    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterViewModel::class)
    abstract fun bindLoginRegisterViewModel(loginRegisterViewModel: LoginRegisterViewModel): ViewModel



}