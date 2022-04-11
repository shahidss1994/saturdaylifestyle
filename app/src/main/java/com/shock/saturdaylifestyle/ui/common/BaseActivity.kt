package com.shock.saturdaylifestyle.ui.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<out T : ViewDataBinding> : AppCompatActivity() {

    private lateinit var binding: T
    protected fun binding() = binding

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun listenChannel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.executePendingBindings()
        listenChannel()
    }

}