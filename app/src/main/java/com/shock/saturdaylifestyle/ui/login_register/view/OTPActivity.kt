package com.shock.saturdaylifestyle.ui.login_register.view
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
import com.shock.saturdaylifestyle.ui.login_register.viewmodel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.ui.main.view.HomeActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import javax.inject.Inject

class OTPActivity : BaseDataBindingActivity<OTPActivityDataBinding>(R.layout.activity_otp),
    OTPActivityViewCallBacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var vm: LoginRegisterViewModel

    private val TAG = OTPActivity::class.java.simpleName

    var otp =""
    var country_code =""
    var phone_wihout_code =""
    var actualOtp ="12345"
    var isUserExist = false

    override fun onDataBindingCreated() {
        binding.callback = this
        binding.lifecycleOwner = this
        supportActionBar!!.hide()
        initOtpViewListener()
        initTimer()
        setObservers()
        getIntents()
    }

    private fun getIntents() {
        if (intent.extras!=null)
        {
            var phone_no = intent.getStringExtra("number")
             country_code = intent.getStringExtra("country_code").toString()
             phone_wihout_code = intent.getStringExtra("phone_wihout_code").toString()
             isUserExist = intent.getBooleanExtra("isUserExist",false)
             binding.tvPhoneno.text = "($phone_no)"
        }
    }

    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
        vm = ViewModelProvider(this, viewModelFactory).get(LoginRegisterViewModel::class.java)
        //   setupObserver()
    }

    private fun initTimer() {

        binding.tvTimer.visibility=View.VISIBLE
        binding.tvTryAgain.visibility=View.GONE

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text ="Try again in "+ millisUntilFinished / 1000

            }

            override fun onFinish() {
                binding.tvTimer.visibility=View.GONE
                binding.tvTryAgain.visibility=View.VISIBLE

            }
        }
        timer.start()    }


    private fun initOtpViewListener() {


        binding.pvPin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                validateOTP()

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    fun validateOTP()
    {
        otp = binding.pvPin.text.toString()

        if (otp.length==6)
        {
            vm.verifyOTP(Constants.API_KEY,binding.pvPin.text.toString().trim())
            CommonUtilities.showLoader(this)
        }else
        {
            binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
            binding.tvWrongNumber.visibility=View.INVISIBLE
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

                        if (isUserExist) {


                            var deviceToken = CommonUtilities.getDeviceToken(this).toString()
                            //hit login api
                            vm.loginUser(phone_wihout_code, actualOtp, country_code, deviceToken)
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

                        binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
                        binding.tvWrongNumber.visibility = View.INVISIBLE
                        binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));

                    }


                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)

                    binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
                    binding.tvWrongNumber.visibility=View.INVISIBLE
                    binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));

                }
            }
        })
        vm.loginUser.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {
                    CommonUtilities.hideLoader()

                    if (it.data?.status == true) {
                        var data = it.data

                        CommonUtilities.showToast(this,data.message.toString())

                        // goto home activity

                        CommonUtilities.fireActivityIntent(
                            this,
                            Intent(this, HomeActivity::class.java),
                            isFinish = true,
                            isForward = true
                        )


                    }else
                    {
                        CommonUtilities.showToast(this, it.message)

                    }

                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)

                }
            }
        })
    }


}