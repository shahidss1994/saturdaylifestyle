package com.shock.saturdaylifestyle.ui.splash.activity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.ActivitySplashBinding
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.loginRegister.activity.LoginRegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding().apply {
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                startActivity(Intent(this@SplashActivity, LoginRegisterActivity::class.java))
                finish()
            }
        }
    }

    override fun listenChannel() {

    }
}
