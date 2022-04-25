package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.FragmentConfirmYourNumberBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BaseFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmYourNumberFragment : BaseFragment<FragmentConfirmYourNumberBinding>(R.layout.fragment_confirm_your_number) {

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewModel = mViewModel
        }
    }

    override fun listenChannel() {

    }

}