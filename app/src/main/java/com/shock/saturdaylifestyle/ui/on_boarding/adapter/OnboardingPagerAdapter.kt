package com.saturdays.on_boarding.adapter

/*
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.shock.saturdaylifestyle.ui.on_boarding.model.ViewPagerDM
import com.shock.saturdaylifestyle.R


class OnboardingPagerAdapter(context: Context, pagerList:ArrayList<ViewPagerDM>): PagerAdapter() {
    internal var context: Context = context
    private var pager:ArrayList<ViewPagerDM> = pagerList

    override fun isViewFromObject(view: View, o:Any):Boolean {
        return view === o
    }
    override fun instantiateItem(container: ViewGroup, position:Int):Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_pager_item, container, false)
        val imageView = view.findViewById(R.id.iv_onboarding) as ImageView
        val heading1 = view.findViewById(R.id.tv_heading1) as TextView
        val text1 = view.findViewById(R.id.tv_subTitle1) as TextView


        imageView.setBackgroundResource(pager[position].image!!)
        heading1.text = pager[position].text1!!
        text1.text = pager[position].text2!!

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position:Int, `object`:Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return pager.size
    }

    override fun getItemPosition(`object`:Any):Int {
        return super.getItemPosition(`object`)
    }
}*/
