package com.shock.saturdaylifestyle.ui.loginRegister.activity

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ActivityLoginRegisterBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.others.observeInLifecycle
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.ConfirmYourNumberFragmentDirections
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.CountryCodeNumberFragment
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOnboardingIntroFragmentDirections
import com.shock.saturdaylifestyle.ui.loginRegister.fragment.LoginOrCreateAccountFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class LoginRegisterActivity :
    BaseActivity<ActivityLoginRegisterBinding>(R.layout.activity_login_register),
    DatePickerDialog.OnDateSetListener {

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
                            navController.navigate(ConfirmYourNumberFragmentDirections.actionLoginOnboardingIntroRegisterFormFragment())
                        }
                    }
                }
                is LoginRegisterViewModel.Event.PickerDialog -> {
                    countryCodeFragment.show(supportFragmentManager, CountryCodeNumberFragment.TAG)
                }
                is LoginRegisterViewModel.Event.PickerDialogClose -> {
                    countryCodeFragment.dismiss()
                }
                is LoginRegisterViewModel.Event.DatePickerClicked -> {
                    val calendar = Calendar.getInstance()
                    val mYear = calendar.get(Calendar.YEAR)
                    val mMonth = calendar.get(Calendar.MONTH)
                    val mDay = calendar.get(Calendar.DAY_OF_MONTH)
                    var datePickerDialog =
                        DatePickerDialog(this, R.style.date_picker_theme, this, mYear, mMonth, mDay)
                    datePickerDialog.datePicker.maxDate = calendar.timeInMillis
                    datePickerDialog.show()
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                        .setTextColor(Color.BLACK)
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                        .setTextColor(Color.BLACK)

                }
                is LoginRegisterViewModel.Event.OnBackPressed -> {
                    onBackPressed()
                }
                is LoginRegisterViewModel.Event.SendOtpViaMissedCallResponse -> {
                    when (it.response.status) {

                        true -> {
                            if (loginOrCreateFragment.isVisible) {
                                loginOrCreateFragment.dismiss()
                            }
                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroMissedCallVerifyYourNumberFragment())
                            //CommonUtilities.showToast(this,it.response.message?.en.toString())
                        }
                        false -> {
                            CommonUtilities.showToast(this, it.response.message?.en.toString())
                        }
                    }

                }
                is LoginRegisterViewModel.Event.SendOtpViaSMSResponse -> {
                    if (it.response?.status == true) {


                        if (it.response.data?.isUserExist == true) {
                            mViewModel.viewState.registerFlow = 0
                        } else {
                            mViewModel.viewState.registerFlow = 1
                        }



                        if (loginOrCreateFragment.isVisible) {
                            loginOrCreateFragment.dismiss()
                        }

                        if (mViewModel.viewState.sendOtpSmsTryAgainClickCount == 0) {


                            navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroConfirmYourNumberFragment())

                        }
                    } else {
                        if (it.response != null) {
                            CommonUtilities.showToast(this, it.response?.message?.en.toString())
                        } else {
                            CommonUtilities.showToast(
                                this,
                                resources.getString(R.string.otp_send_error_msg)
                            )
                        }
                    }

                }
                is LoginRegisterViewModel.Event.VerifyOtpResponse -> {
                    if (it.response?.status == true) {

                        CommonUtilities.showToast(this, it.response?.message?.en.toString())

                        navController.navigate(LoginOnboardingIntroFragmentDirections.actionLoginOnboardingIntroRegisterFormFragment())


                    } else {
                        if (it.response != null) {
                            CommonUtilities.showToast(this, it.response?.message?.en.toString())
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


            is LoginRegisterViewModel.Event.ToggleLoader -> {
            if (it.isToShow) {
                CommonUtilities.showLoader(this@LoginRegisterActivity)
            } else {
                CommonUtilities.hideLoader()
            }
        }
        }
    }.observeInLifecycle(this@LoginRegisterActivity )
}


@RequiresApi(Build.VERSION_CODES.O)
override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    val calendar2: Calendar = Calendar.getInstance()
    calendar2.set(year, month, dayOfMonth, 0, 0, 0)
    val selectedDate = calendar2.time
    val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
    var date = dateFormat.format(selectedDate)

    mViewModel.registerFormModel.dob = date
    mViewModel.registerFormViewState.dob = date

}

}