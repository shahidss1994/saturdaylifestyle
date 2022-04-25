package com.shock.saturdaylifestyle.ui.on_boarding.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.saturdays.on_boarding.adapter.OnboardingPagerAdapter
import com.saturdays.on_boarding.model.ViewPagerDM
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.OoboardingActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.ui.main.view.HomeActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class OnboardingActivity : BaseDataBindingActivity<OoboardingActivityDataBinding>(R.layout.onboarding_screen_layout),
    OnBoardingCallbacks {
    var whichScreen: String? = null
    var pos: Int? = 0
    var pager_itemList = ArrayList<ViewPagerDM>()
    private lateinit var btn_login_create_account : Button

   // private val mViewModel: OnBoardingViewModel by viewModels()





    override fun onDataBindingCreated() {
        binding.callback = this
        binding.lifecycleOwner = this
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()


        btn_login_create_account = findViewById(R.id.btn_login_create_account)
        btn_login_create_account.setOnClickListener {
            onLoginBtnClick()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }


        /**
         *
         *   going to onboarding screen from splash
         *
         * */
        pager_itemList.clear()
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding1,
                "ANYWHERE IN THE WORLD!",
                "Enjoy free shipping all across the globe\nonly for you!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "PRICE INCLUDES\nPRESCRIPTION LENSES",
                "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "HOME TRY ON",
                "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "SAVE MORE BUCKS!",
                "Enjoy our exclusive promos as well as our\nloyalty program to save some more!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding5,
                "OFFLINE STORES",
                "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia."
            )
        )



        val adapter = OnboardingPagerAdapter(this, pager_itemList)
        binding?.viewpager!!.adapter = adapter
        binding?.dotsIndicator!!.setViewPager(binding?.viewpager!!)
        binding?.viewpager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                binding?.btnLoginCreateAccount!!.setOnClickListener {

                    CommonUtilities.fireActivityIntent(
                        this@OnboardingActivity,
                        Intent(this@OnboardingActivity, LoginRegisterActivity::class.java),
                        isFinish = true,
                        isForward = true
                    )

                }

            }

        })


        binding.tvSkip.setOnClickListener {

            CommonUtilities.putBoolean(this@OnboardingActivity,Constants.IS_GUEST,true)

            CommonUtilities.fireActivityIntent(
                this@OnboardingActivity,
                Intent(this@OnboardingActivity, HomeActivity::class.java),
                isFinish = true,
                isForward = true
            )


        }


    }
    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onLoginBtnClick() {
        startActivity(Intent(this, LoginRegisterActivity::class.java))
    }


}