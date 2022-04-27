package com.shock.saturdaylifestyle.ui.loginRegister.activity

import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ActivityLoginRegisterBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.CountryCodeNumberFragment
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOnboardingIntroFragmentDirections
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOrCreateAccountFragment
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.MissedCallVerifyYourNumberFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginRegisterActivity :
    BaseActivity<ActivityLoginRegisterBinding>(R.layout.activity_login_register) {

    private val mViewModel: LoginRegisterViewModel by viewModels()

    override fun listenChannel() {

        val loginOrCreateFragment = LoginOrCreateAccountFragment()
        val countryCodeFragment = CountryCodeNumberFragment()

        val navController = findNavController(R.id.navHostFragment)

        mViewModel.eventFlow.onEach {
            when (it) {
                is LoginRegisterViewModel.Event.NavigateTo -> {
                    when (it.navigateTo) {
                        Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT -> {
                            loginOrCreateFragment.show(
                                supportFragmentManager,
                                LoginOrCreateAccountFragment.TAG
                            )
                        }
                        Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER -> {
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroWhatsappVerifyYourNumberFragment())
                        }
                        Constants.NavigateTo.CONFIRM_YOUR_NUMBER -> {
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroConfirmYourNumberFragment())
                        }
                        Constants.NavigateTo.MISSED_CALL_VERIFY_YOUR_NUMBER -> {
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroMissedCallVerifyYourNumberFragment())
                        }
                        Constants.NavigateTo.REGISTER_FORM -> {
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroRegisterFormFragment())
                        }
                    }
                }
                is LoginRegisterViewModel.Event.PickerDialog -> {
                    countryCodeFragment.show(supportFragmentManager, CountryCodeNumberFragment.TAG)
                }
                is LoginRegisterViewModel.Event.PickerDialogClose -> {
                    countryCodeFragment.dismiss()
                }
                is LoginRegisterViewModel.Event.OnBackPressed -> {
                    onBackPressed()
                }
                is LoginRegisterViewModel.Event.onSendOtpViaMissedCallResponse -> {
                    when (it.response.status) {

                        true ->{
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroMissedCallVerifyYourNumberFragment())
                            CommonUtilities.showToast(this,it.response.message?.en.toString())
                        }
                        false ->{
                            CommonUtilities.showToast(this,it.response.message?.en.toString())
                        }
                    }

                }
                is LoginRegisterViewModel.Event.onSendOtpViaSMSResponse -> {
                    when (it.response.status) {

                        true ->{
                            if(loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            CommonUtilities.showToast(this,it.response.message?.en.toString())

                            if(mViewModel.viewState.sendOtpSmsTryAgainClickCount==0) {
                                navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroConfirmYourNumberFragment())
                            }


                            mViewModel.viewState.sendOtpSmsTryAgainVisibility = true
                            val timer = object : CountDownTimer(30000, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        mViewModel.viewState.smstryAgainTimerText = "Try again in " + millisUntilFinished / 1000

                                    }

                                    override fun onFinish() {
                                        mViewModel.viewState.sendOtpSmsTryAgainVisibility = false

                                    }
                                }
                                timer.start()



                        }
                        false ->{
                            CommonUtilities.showToast(this,it.response.message?.en.toString())
                        }
                    }

                }
            }
        }.observeInLifecycle(this@LoginRegisterActivity)
    }

}