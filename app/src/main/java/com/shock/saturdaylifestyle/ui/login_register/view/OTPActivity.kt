package com.shock.saturdaylifestyle.ui.login_register.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.ui.login_register.callbacks.OTPActivityViewCallBacks
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.OTPActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelFactory
import com.shock.saturdaylifestyle.network.Status
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.ui.login_register.models.Message
import com.shock.saturdaylifestyle.ui.login_register.viewmodel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.ui.main.view.HomeActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import com.shock.saturdaylifestyle.utility.MyBottomSheetDialog
import org.json.JSONObject
import javax.inject.Inject

class OTPActivity : BaseDataBindingActivity<OTPActivityDataBinding>(R.layout.activity_otp),
    OTPActivityViewCallBacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var vm: LoginRegisterViewModel

    private val TAG = OTPActivity::class.java.simpleName

    var otp = ""
    var country_code = ""
    var phone_wihout_code = ""
    var isUserExist = false

    var tryAgainClickCount = 0

    override fun onDataBindingCreated() {
        binding.callback = this
        binding.lifecycleOwner = this
        supportActionBar!!.hide()
        initOtpViewListener()
        initTimer()
        setObservers()
        getIntents()



        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivBack2.setOnClickListener {
            onBackPressed()
        }
        binding.ivBack3.setOnClickListener {
            onBackPressed()
        }

        binding.tvTryAgain.setOnClickListener {


            if (tryAgainClickCount < 2) {
                otp = ""
                binding.pvPin.setText("")
                tryAgainClickCount++

                vm.sendOTP(
                    Constants.API_KEY,
                    LoginRegisterActivity.phone_number,
                    LoginRegisterActivity.country_code
                )
                CommonUtilities.showLoader(this)
            } else {

                showBottomSheetStillDitNotGetOTP()
            }
        }
    }

    private fun getIntents() {
        if (intent.extras != null) {
            var phone_no = intent.getStringExtra("number")
            var otpType = intent.getIntExtra("otpType", 0)
            country_code = intent.getStringExtra("country_code").toString()
            phone_wihout_code = intent.getStringExtra("phone_wihout_code").toString()
            isUserExist = intent.getBooleanExtra("isUserExist", false)
            binding.tvPhoneno.text = "($phone_no)"


            if (otpType == 1) {

                binding.clOtp.visibility = View.VISIBLE
                binding.clOtpWhatsapp.visibility = View.GONE
                binding.clMissCall.visibility = View.GONE

            } else if (otpType == 2) {
                binding.clOtp.visibility = View.GONE
                binding.clMissCall.visibility = View.GONE
                binding.clOtpWhatsapp.visibility = View.VISIBLE
            } else if (otpType == 3) {
                binding.clOtp.visibility = View.GONE
                binding.clOtpWhatsapp.visibility = View.GONE
                binding.clMissCall.visibility = View.VISIBLE
            }
        }
    }

    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
        vm = ViewModelProvider(this, viewModelFactory).get(LoginRegisterViewModel::class.java)
        //   setupObserver()
    }

    private fun initTimer() {

        binding.tvTimer.visibility = View.VISIBLE
        binding.tvTryAgain.visibility = View.GONE

        val timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = "Try again in " + millisUntilFinished / 1000

            }

            override fun onFinish() {
                binding.tvTimer.visibility = View.GONE
                binding.tvTryAgain.visibility = View.VISIBLE

            }
        }
        timer.start()
    }


    private fun initOtpViewListener() {


        binding.pvPin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                validateOTP()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    fun validateOTP() {
        otp = binding.pvPin.text.toString()

        if (otp.length == 6) {
            //  vm.verifyOTP(Constants.API_KEY,otp)


            if (isUserExist) {


                var deviceToken = CommonUtilities.getDeviceToken(this).toString()
                //hit login api
                vm.loginUser(phone_wihout_code, otp, country_code, deviceToken)


            } else {
                //    CommonUtilities.hideLoader()
                CommonUtilities.fireActivityIntent(
                    this,
                    Intent(this, RegisterForm1Activity::class.java),
                    isFinish = false,
                    isForward = true
                )

            }


        } else {
            binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
            binding.tvWrongNumber.visibility = View.INVISIBLE
            binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));
        }
    }


    private fun setObservers() {
        vm.verifyOTP.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {

                    if (it.data?.status == true) {

                        binding.pvPin.setTextColor(Color.parseColor("#3A6A67"))
                        binding.tvWrongNumber.visibility = View.INVISIBLE
                        binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));


                        if (isUserExist) {

                            var deviceToken = CommonUtilities.getDeviceToken(this).toString()
                            //hit login api
                            vm.loginUser(phone_wihout_code, otp, country_code, deviceToken)
                            CommonUtilities.showLoader(this)


                        } else {
                            //    CommonUtilities.hideLoader()
                            CommonUtilities.fireActivityIntent(
                                this,
                                Intent(this, RegisterForm1Activity::class.java),
                                isFinish = false,
                                isForward = true
                            )

                        }


                    } else {
                        // CommonUtilities.hideLoader()

                        binding.pvPin.setTextColor(Color.parseColor("#B5564C"))
                        binding.tvWrongNumber.visibility = View.VISIBLE
                        binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));

                    }


                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()

                    // CommonUtilities.showToast(this,it.message)

                    binding.pvPin.setTextColor(Color.parseColor("#B5564C"))
                    binding.tvWrongNumber.visibility = View.VISIBLE
                    binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));

                }
            }
        })
        vm.loginUser.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {
                    //  CommonUtilities.hideLoader()

                    if (it.data?.status == true) {
                        var data = it.data

                        binding.pvPin.setTextColor(Color.parseColor("#3A6A67"))
                        binding.tvWrongNumber.visibility = View.INVISIBLE
                        binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));

                        CommonUtilities.putString(
                            this,
                            Constants.TOKEN,
                            data.data?.token.toString()
                        )
                        CommonUtilities.putString(this, Constants.NAME, data.data?.name.toString())
                        CommonUtilities.putBoolean(this, Constants.IS_LOGIN, true)
                        CommonUtilities.putBoolean(this, Constants.IS_GUEST, false)


                        // goto home activity
                        CommonUtilities.fireActivityIntent(
                            this,
                            Intent(this, HomeActivity::class.java),
                            isFinish = true,
                            isForward = true
                        )


                    } else {

                        CommonUtilities.showToast(this, it.message.toString())
                        binding.pvPin.setTextColor(Color.parseColor("#B5564C"))
                        binding.tvWrongNumber.visibility = View.VISIBLE
                        binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));


                    }

                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)

                    binding.pvPin.setTextColor(Color.parseColor("#B5564C"))
                    binding.tvWrongNumber.visibility = View.VISIBLE
                    binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));


                }
            }
        })
        vm.sendOTP.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {
                    CommonUtilities.hideLoader()

                    initTimer()


                    CommonUtilities.showToast(this, it.data?.message?.en.toString())

                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)

                }
            }
        })
    }


    private fun showBottomSheetStillDitNotGetOTP() {


        var bottomSheetDialog = MyBottomSheetDialog(this, false)


        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_still_didnt_get_otp, null)


        (view.findViewById(R.id.iv_back) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
            //   showBottomSheetWhatsapp()
        }

        (view.findViewById(R.id.tv_phone)as TextView).setText(country_code+phone_wihout_code)

        (view.findViewById(R.id.tv_skip_for_now) as View).setOnClickListener {

            bottomSheetDialog.dismiss()


        }


        (view.findViewById(R.id.rl_whatsapp) as View).setOnClickListener {

            bottomSheetDialog.dismiss()


        }

        (view.findViewById(R.id.rl_google) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }


}