package com.shock.saturdaylifestyle.ui.loginRegister.activity

import android.os.Bundle
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivityLoginRegisterBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginRegisterActivity : BaseActivity<ActivityLoginRegisterBinding>(R.layout.activity_login_register) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun listenChannel() {

    }
}