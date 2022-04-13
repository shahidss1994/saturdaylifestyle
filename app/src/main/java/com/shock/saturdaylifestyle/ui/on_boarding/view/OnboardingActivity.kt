package com.shock.saturdaylifestyle.ui.on_boarding.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.saturdays.on_boarding.adapter.OnboardingPagerAdapter
import com.saturdays.on_boarding.model.ViewPagerDM
import com.saturdays.on_boarding.viewmodel.OnboardingVM
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.OnboardingScreenLayoutBinding
import com.shock.saturdaylifestyle.utility.CommonUtilities


class OnboardingActivity : AppCompatActivity() {
    var binding: OnboardingScreenLayoutBinding? = null
    var vm: OnboardingVM? = null
    var whichScreen: String? = null
    var pos: Int? = 0
    var pager_itemList = ArrayList<ViewPagerDM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        binding = DataBindingUtil.setContentView(this, R.layout.onboarding_screen_layout)


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


        binding?.tvSkip!!.setOnClickListener {

            CommonUtilities.fireActivityIntent(
                this@OnboardingActivity,
                Intent(this@OnboardingActivity, LoginRegisterActivity::class.java),
                isFinish = true,
                isForward = true
            )


        }


    }



}