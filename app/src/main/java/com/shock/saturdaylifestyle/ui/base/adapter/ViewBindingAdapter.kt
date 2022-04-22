package com.shock.saturdaylifestyle.ui.base.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.shock.saturdaylifestyle.ui.base.viewModel.DrawableViewModel

@BindingAdapter("items")
fun <T> ViewPager2.setItems(items: List<T>) {
    val tempAdapter = adapter
    if (tempAdapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (tempAdapter as BindableAdapter<T>).items = items
    } else {
        throw IllegalStateException("Your adapter should implement BindableAdapter")
    }
}

@BindingAdapter("imageDrawableViewModel")
fun AppCompatImageView.setImageDrawableViewModel(drawableViewModel: DrawableViewModel?) {
    drawableViewModel?.drawable?.let {
        this.setImageResource(it)
    }
}