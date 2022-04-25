package com.shock.saturdaylifestyle.ui.main.view

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.HomeActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.ui.main.adapters.HomeViewPagerAdapter
import com.shock.saturdaylifestyle.ui.main.models.HomeViewPagerDM

class HomeActivity : BaseDataBindingActivity<HomeActivityDataBinding>(R.layout.activity_home) {

        override fun onDataBindingCreated() {
            //  binding.callback = this
            binding.lifecycleOwner = this
            supportActionBar!!.hide()

            setViewPager()

            setAutoStartMarquee(binding.tvMarqueeText,true)

        }

        @BindingAdapter("app:autoStartMarquee")
        fun setAutoStartMarquee(textView: TextView, autoStartMarquee: Boolean) {
            textView.isSelected = autoStartMarquee
        }

        override fun injectDaggerComponent() {
            DaggerProvider.getAppComponent()?.inject(this)
        }



    private val handler = Handler(Looper.getMainLooper())
    private fun setViewPager() {
        val viewpagerDataList = ArrayList<HomeViewPagerDM>()
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
        viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))

        val adapter = HomeViewPagerAdapter(viewpagerDataList)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val newPosition = (position + 1) % viewpagerDataList.size
                val runnable = Runnable { binding.viewPager.currentItem = newPosition }
                handler.postDelayed(runnable, 3000L)
            }
        })
    }


    override fun onBackPressed() {
            super.onBackPressed()
            finishAffinity()
        }


}