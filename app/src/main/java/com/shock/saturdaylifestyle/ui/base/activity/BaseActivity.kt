package com.shock.saturdaylifestyle.ui.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shock.saturdaylifestyle.utility.CommonUtilities

abstract class BaseActivity<out T : ViewDataBinding>(@LayoutRes private val screenLayoutId: Int) :
    AppCompatActivity() {

    private lateinit var binding: T
    protected fun binding() = binding

    abstract fun listenChannel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, screenLayoutId)
        setContentView(binding.root)
        binding.executePendingBindings()
        listenChannel()
    }

    fun toggleLoader(isToShow: Boolean? = false) {
        if (isToShow == true) {
            CommonUtilities.showLoader(this@BaseActivity)
        } else {
            CommonUtilities.hideLoader()
        }
    }

    fun showToast(msg:String){
        CommonUtilities.showToast(this, msg)
    }

}