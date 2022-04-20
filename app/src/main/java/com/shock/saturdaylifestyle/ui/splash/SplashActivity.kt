package com.shock.saturdaylifestyle.ui.splash

import android.content.Intent
import android.os.Handler
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.ui.on_boarding.view.OnboardingActivity
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.RegisterForm1ActivityDataBinding
import com.shock.saturdaylifestyle.databinding.SplashActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities

class SplashActivity : BaseDataBindingActivity<SplashActivityDataBinding>(R.layout.activity_splash)
    {


        override fun onDataBindingCreated() {
          //  binding.callback = this
            binding.lifecycleOwner = this
            supportActionBar!!.hide()
            splashHandler()

        }
        override fun injectDaggerComponent() {
            DaggerProvider.getAppComponent()?.inject(this)
        }


    private fun splashHandler() {
        Handler().postDelayed({

            if (!CommonUtilities.getBoolean(this, Constants.IS_LOGIN)) {

                CommonUtilities.fireActivityIntent(
                    this,
                    Intent(this, OnboardingActivity::class.java),
                    isFinish = true,
                    isForward = true
                )

            } else {

                CommonUtilities.fireActivityIntent(
                    this,
                    Intent(this, LoginRegisterActivity::class.java),
                    isFinish = true,
                    isForward = true
                )

            }

        }, 2000)

    }
}