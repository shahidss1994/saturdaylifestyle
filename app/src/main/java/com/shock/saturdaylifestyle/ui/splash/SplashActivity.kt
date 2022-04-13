package com.shock.saturdaylifestyle.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.ui.on_boarding.view.OnboardingActivity
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ActivitySplashBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(){


    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun listenChannel() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashHandler()
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