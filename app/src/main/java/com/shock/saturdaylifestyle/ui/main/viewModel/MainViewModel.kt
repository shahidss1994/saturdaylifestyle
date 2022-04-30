package com.shock.saturdaylifestyle.ui.main.viewModel

import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState
import com.shock.saturdaylifestyle.ui.base.viewState.StringViewState
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterRepository
import com.shock.saturdaylifestyle.ui.main.viewState.DetailsMakeUsDifferentItemViewState
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
        val homeViewStateList = arrayListOf<HomeViewState>()
        homeViewStateList.add(HomeViewState(1,HeaderViewState(),Constants.SectionName.Home.HEADER))
        val detailsMakeUsDifferentItemViewStateList = arrayListOf<DetailsMakeUsDifferentItemViewState>()
        detailsMakeUsDifferentItemViewStateList.add(DetailsMakeUsDifferentItemViewState(1,
            DrawableViewState(R.drawable.detail_make_us_different_originality),
            StringViewState(R.string.originality),
            StringViewState(R.string.originality_desc)
        ))
        detailsMakeUsDifferentItemViewStateList.add(DetailsMakeUsDifferentItemViewState(2,
            DrawableViewState(R.drawable.detail_make_us_different_only_the_good_stuff),
            StringViewState(R.string.only_the_good_stuff),
            StringViewState(R.string.only_the_good_stuff_desc)
        ))
        homeViewStateList.add(HomeViewState(2,DetailsMakeUsDifferentViewState("1",detailsMakeUsDifferentItemViewStateList),Constants.SectionName.Home.DETAILS_MAKE_US_DIFFERENT))
        homeViewStateList.add(HomeViewState(3,ExploreOurTopPicksViewState(),Constants.SectionName.Home.TOP_PICKS))
        homeViewStateList.add(HomeViewState(4,NewArrivalViewState(),Constants.SectionName.Home.NEW_ARRIVALS))
        homeViewStateList.add(HomeViewState(5,HomeTryOnViewState(),Constants.SectionName.Home.HOME_TRY_ON))
        homeViewStateList.add(HomeViewState(6,WhatTheySayViewState(),Constants.SectionName.Home.WHAT_THEY_SAY))
        mainViewState.homeItemList = homeViewStateList
    }

    

}