package com.shock.saturdaylifestyle.ui.loginRegister.activity

import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ActivityLoginRegisterBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.fragment.DatePickerDialogFragment
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.home.activity.HomeActivity
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.CountryCodeNumberFragment
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOnboardingIntroFragmentDirections
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOrCreateAccountFragment
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
        val datePickerDialogFragment = DatePickerDialogFragment()

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
                            if (loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroWhatsappVerifyYourNumberFragment())
                        }
                        Constants.NavigateTo.CONFIRM_YOUR_NUMBER -> {
                            if (loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroConfirmYourNumberFragment())
                        }
                        Constants.NavigateTo.MISSED_CALL_VERIFY_YOUR_NUMBER -> {
                            if (loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroMissedCallVerifyYourNumberFragment())
                        }
                        Constants.NavigateTo.REGISTER_FORM -> {
                            if (loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            onBackPressed()
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
                is LoginRegisterViewModel.Event.SendOtpViaMissedCallResponse -> {
                    if (it.response.status == true) {
                        if (loginOrCreateFragment.isVisible) {
                            loginOrCreateFragment.dismiss()
                        }
                        navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroMissedCallVerifyYourNumberFragment())
                    } else {
                        showToast(it.response.message?.en ?: "Network error")
                    }
                }
                is LoginRegisterViewModel.Event.ToggleLoader -> {
                    toggleLoader(it.isToShow)
                }
                is LoginRegisterViewModel.Event.DateDialogPicker -> {
                    if (datePickerDialogFragment.isVisible) {
                        datePickerDialogFragment.dismiss()
                    } else {
                        datePickerDialogFragment.show(supportFragmentManager,
                            object : DatePickerDialogFragment.DateSelectedListener {
                                override fun onDateSelected(
                                    year: Int,
                                    monthOfYear: Int,
                                    dayOfMonth: Int
                                ) {
                                    mViewModel.onDateSelected(year, monthOfYear, dayOfMonth)
                                }

                            })
                    }
                }
                is LoginRegisterViewModel.Event.SendOtpViaSMSResponse -> {
                    if (it.response?.status == true) {
                        if (loginOrCreateFragment.isVisible) {
                            loginOrCreateFragment.dismiss()
                        }
                        if (mViewModel.viewState.sendOtpSmsTryAgainClickCount < 3) {
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroConfirmYourNumberFragment())
                        }else{

                        }
                    } else {
                        if (it.response != null) {
                            showToast(it.response?.message?.en ?: "Network error")
                        } else {
                            showToast(resources.getString(R.string.otp_send_error_msg))
                        }
                    }
                }
                is LoginRegisterViewModel.Event.VerifyOtpResponse -> {
                    if (it.response?.status == true) {
                        if (it.isUserExist == true) {
                            startActivity(
                                Intent(
                                    this@LoginRegisterActivity,
                                    HomeActivity::class.java
                                )
                            )
                            finish()
                        } else {
                            onBackPressed()
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroRegisterFormFragment())
                        }
                    } else {
                        if (it.response != null) {
                            showToast(it.response?.message?.en ?: "Network error")
                        } else {
                            showToast(resources.getString(R.string.unable_to_login))
                        }
                    }

                }
                is LoginRegisterViewModel.Event.RegisterResponse -> {
                    if (it.response?.status == true) {

                        CommonUtilities.showToast(this, it.response.message.toString())

/*
                        var data = it.response.data
                        CommonUtilities.putString(this, Constants.TOKEN, data?.token.toString())
                        CommonUtilities.putString(this, Constants.NAME, data?.name.toString())
                        CommonUtilities.putBoolean(this, Constants.IS_LOGIN, true)

                        */
                        // CommonUtilities.putBoolean(this, Constants.IS_GUEST,false)


                    } else {


                        //  CommonUtilities.showToast(this, it.response?.message.toString())


                    }
                }
            }

        }.observeInLifecycle(this@LoginRegisterActivity)
    }


}