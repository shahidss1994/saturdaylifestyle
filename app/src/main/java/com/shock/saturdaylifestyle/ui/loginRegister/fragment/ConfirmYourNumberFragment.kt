package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.FragmentConfirmYourNumberBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BaseFragment
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ConfirmYourNumberFragment : BaseFragment<FragmentConfirmYourNumberBinding>(R.layout.fragment_confirm_your_number) {

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
        }
    }

    override fun listenChannel() {


        mViewModel.eventFlow.onEach {
            when (it) {


                is LoginRegisterViewModel.Event.onSendOTPViaSMSTryAgainClicked -> {

                    CommonUtilities.showLoader(requireActivity())

                    mViewModel.sendOtpViaSMSApi(
                        Constants.API_KEY,
                        mViewModel.viewState.phoneNo,
                        mViewModel.viewState.countryCodeNumberViewState.code.toString()
                    )

                }
                is LoginRegisterViewModel.Event.OnBackPressed -> {

                }

            }
        }.observeInLifecycle(this)
    }

}