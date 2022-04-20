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
import com.shock.saturdaylifestyle.ui.login_register.callbacks.OTPActivityViewCallBacks
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.OTPActivityDataBinding
import com.shock.saturdaylifestyle.network.Status
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import com.shock.saturdaylifestyle.ui.common.bind
import com.shock.saturdaylifestyle.ui.login_register.viewmodel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPActivity : BaseActivity<OTPActivityDataBinding>(),
    OTPActivityViewCallBacks {

    private val mViewModel: LoginRegisterViewModel by viewModels()


    private lateinit var binding : OTPActivityDataBinding
    private val TAG = OTPActivity::class.java.simpleName

    var otp =""
    var actualOtp ="12345"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding()
        initOtpViewListener()


        initTimer()

        binding.tvTryAgain.setOnClickListener {
            initTimer()
        }

        setObservers()

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

        if (otp.length==5)
        {


            mViewModel.verifyOTP(Constants.API_KEY,binding.pvPin.text.toString().trim())
            CommonUtilities.showLoader(this)



            if (otp == actualOtp)
            {
                //CommonUtilities.showToast(this,"success")
                binding.pvPin.setTextColor(Color.parseColor("#3A6A67"))

            }else
            {
                binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
                binding.tvWrongNumber.visibility=View.VISIBLE
                binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));

            }

        }else
        {
            binding.pvPin.setTextColor(Color.parseColor("#2A2F32"))
            binding.tvWrongNumber.visibility=View.INVISIBLE
            binding.pvPin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));


        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_otp
    }

    override fun listenChannel() {

    }

    private fun setObservers() {
        mViewModel.sendOTP.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {
                    CommonUtilities.hideLoader()
                    val data = it.data!!.data

                    if (it.data.status == true)
                    {





                    }
                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)
                }
            }
        });
    }





}