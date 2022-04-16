package com.shock.saturdaylifestyle.ui.login_register.view
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.shock.saturdaylifestyle.ui.login_register.callbacks.OTPActivityViewCallBacks
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.OTPActivityDataBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import com.shock.saturdaylifestyle.ui.common.bind
import com.shock.saturdaylifestyle.utility.CommonUtilities


class OTPActivity : BaseActivity<OTPActivityDataBinding>(),
    OTPActivityViewCallBacks {


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


}