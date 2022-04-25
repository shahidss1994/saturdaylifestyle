package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.os.Bundle
import @android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.FragmentLoginOnboardingIntroBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BaseFragment
import com.shock.saturdaylifestyle.ui.loginRegister.adapter.LoginOnboardingIntroAdapter
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOnboardingIntroFragment: BaseFragment<FragmentLoginOnboardingIntroBinding>(R.layout.fragment_login_onboarding_intro) {

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
            with(viewpager){
                adapter = LoginOnboardingIntroAdapter(mViewModel, mViewModel.viewState)
            }
            dotsIndicator.setViewPager2(viewpager)
        }
    }

    override fun listenChannel() {

    }

}