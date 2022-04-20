package com.shock.saturdaylifestyle.ui.main.view

import android.content.Intent
import android.os.Handler
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.ui.on_boarding.view.OnboardingActivity
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.HomeActivityDataBinding
import com.shock.saturdaylifestyle.databinding.RegisterForm1ActivityDataBinding
import com.shock.saturdaylifestyle.databinding.SplashActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities

class HomeActivity : BaseDataBindingActivity<HomeActivityDataBinding>(R.layout.activity_home) {


        override fun onDataBindingCreated() {
          //  binding.callback = this
            binding.lifecycleOwner = this
            supportActionBar!!.hide()

        }
        override fun injectDaggerComponent() {
            DaggerProvider.getAppComponent()?.inject(this)
        }


}