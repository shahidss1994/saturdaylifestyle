package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.FragmentLoginOrCreateAccountBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BottomSheetBaseFragment
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginOrCreateAccountFragment: BottomSheetBaseFragment<FragmentLoginOrCreateAccountBinding>(R.layout.fragment_login_or_create_account) {

    companion object {
        const val TAG = "LoginOrCreateAccountFragment"
    }

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
        }
    }

    override fun listenChannel() {




    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.viewState.loginOrCreateAccountVisibility = true
    }

}