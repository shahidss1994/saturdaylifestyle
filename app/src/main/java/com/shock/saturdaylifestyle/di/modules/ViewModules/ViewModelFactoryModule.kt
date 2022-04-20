package com.shock.saturdaylifestyle.di.modules.ViewModules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider


@Module
class ViewModelFactoryModule {

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return ViewModelFactory(providerMap)
    }
}
