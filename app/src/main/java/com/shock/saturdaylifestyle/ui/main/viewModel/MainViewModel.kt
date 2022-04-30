package com.shock.saturdaylifestyle.ui.main.viewModel

import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterRepository
import com.shock.saturdaylifestyle.ui.main.viewState.DetailsMakeUsDifferentViewState
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreOurTopPicksViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HeaderViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HomeTryOnViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HomeViewState
import com.shock.saturdaylifestyle.ui.main.viewState.MainViewState
import com.shock.saturdaylifestyle.ui.main.viewState.NewArrivalViewState
import com.shock.saturdaylifestyle.ui.main.viewState.WhatTheySayViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LoginRegisterRepository
) : BaseViewModel(repository) {

    val mainViewState = MainViewState()

    init {
        val arrayList = arrayListOf<HomeViewState>()
        arrayList.add(HomeViewState(1,HeaderViewState(),Constants.SectionName.Home.HEADER))
        arrayList.add(HomeViewState(2,DetailsMakeUsDifferentViewState(),Constants.SectionName.Home.DETAILS_MAKE_US_DIFFERENT))
        arrayList.add(HomeViewState(3,ExploreOurTopPicksViewState(),Constants.SectionName.Home.TOP_PICKS))
        arrayList.add(HomeViewState(4,NewArrivalViewState(),Constants.SectionName.Home.NEW_ARRIVALS))
        arrayList.add(HomeViewState(5,HomeTryOnViewState(),Constants.SectionName.Home.HOME_TRY_ON))
        arrayList.add(HomeViewState(6,WhatTheySayViewState(),Constants.SectionName.Home.WHAT_THEY_SAY))
        mainViewState.homeItemList = arrayList
    }

}