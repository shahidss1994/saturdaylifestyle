package com.shock.saturdaylifestyle.ui.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<out T : ViewDataBinding>(@LayoutRes private val screenLayoutId: Int) :
    AppCompatActivity() {

    private lateinit var binding: T
    protected fun binding() = binding

    abstract fun listenChannel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, screenLayoutId)
        binding.executePendingBindings()
        listenChannel()
    }

}