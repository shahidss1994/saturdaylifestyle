package com.shock.saturdaylifestyle.ui.loginRegister.activity

import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ActivityLoginRegisterBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOrCreateAccountFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginRegisterActivity :
    BaseActivity<ActivityLoginRegisterBinding>(R.layout.activity_login_register) {

    private val mViewModel: LoginRegisterViewModel by viewModels()

    override fun listenChannel() {

        val navController = findNavController(R.id.navHostFragment)

        mViewModel.eventFlow.onEach {
            when (it) {
                is LoginRegisterViewModel.Event.NavigateTo -> {
                    when (it.navigateTo) {
                        Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT -> {
                            val fragment = LoginOrCreateAccountFragment()
                            fragment.show(supportFragmentManager,LoginOrCreateAccountFragment.TAG)
                            //navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroToLoginOrCreateAccount())
                        }
                    }
                }
            }
        }.observeInLifecycle(this@LoginRegisterActivity)
    }

}