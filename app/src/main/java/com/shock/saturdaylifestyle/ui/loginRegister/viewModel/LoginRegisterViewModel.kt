package com.shock.saturdaylifestyle.ui.loginRegister.viewModel

import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.base.viewModel.DrawableViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState
import com.shock.saturdaylifestyle.ui.main.network.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    val viewState = LoginRegisterViewState()

    init {
        val arrayList = arrayListOf<IntroViewPagerItemViewState>()
        val introViewPagerItemViewState1 = IntroViewPagerItemViewState(
            1,
            "ANYWHERE IN THE WORLD!",
            "Enjoy free shipping all across the globe\nonly for you!",
            DrawableViewModel(R.mipmap.iv_onboarding1)
        )
        arrayList.add(introViewPagerItemViewState1)

        val introViewPagerItemViewState2 = IntroViewPagerItemViewState(
            2,
            "PRICE INCLUDES\nPRESCRIPTION LENSES",
            "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState2)

        val introViewPagerItemViewState3 = IntroViewPagerItemViewState(
            3,
            "HOME TRY ON",
            "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState3)

        val introViewPagerItemViewState4 = IntroViewPagerItemViewState(
            4,
            "SAVE MORE BUCKS!",
            "Enjoy our exclusive promos as well as our\nloyalty program to save some more!",
            DrawableViewModel(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState4)

        val introViewPagerItemViewState5 = IntroViewPagerItemViewState(
            5,
            "OFFLINE STORES",
            "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia.",
            DrawableViewModel(R.mipmap.iv_onboarding5)
        )
        arrayList.add(introViewPagerItemViewState5)
        viewState.introViewPagerItemViewStateList = arrayList
    }

    override fun onBackPressed() {

    }

}