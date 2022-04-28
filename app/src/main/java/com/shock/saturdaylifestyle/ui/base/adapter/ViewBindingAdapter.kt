package com.shock.saturdaylifestyle.ui.base.adapter

import android.text.TextWatcher
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.chaos.view.PinView
import com.shock.saturdaylifestyle.ui.base.viewState.ColorViewState
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState

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

@BindingAdapter("items")
fun <T> RecyclerView.setItems(items: List<T>) {
    val tempAdapter = adapter
    if (tempAdapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (tempAdapter as BindableAdapter<T>).items = items
    } else {
        throw IllegalStateException("Your adapter should implement BindableAdapter")
    }
}

@BindingAdapter("drawableViewState")
fun AppCompatImageView.setDrawableViewState(drawableViewModel: DrawableViewState?) {
    drawableViewModel?.drawable?.let {
        this.setImageResource(it)
    }
}

@BindingAdapter("drawableViewState")
fun AppCompatButton.setDrawableViewState(drawableViewModel: DrawableViewState?) {
    drawableViewModel?.drawable?.let {
        this.background = ContextCompat.getDrawable(context, it)
    }
}

@BindingAdapter("colorViewState")
fun AppCompatTextView.setColorViewState(colorViewState: ColorViewState?) {
    colorViewState?.color?.let {
        setTextColor(ContextCompat.getColor(context, it))
    }
}

@BindingAdapter("textChangedListener")
fun AppCompatEditText.setTextChangedListener(textWatcher: TextWatcher?) {
    textWatcher?.let {
        addTextChangedListener(it)
    }
}

@BindingAdapter("checkChangedListener")
fun RadioGroup.setCheckChangedListener(listener: RadioGroup.OnCheckedChangeListener?) {
    listener?.let {
        setOnCheckedChangeListener(listener)
    }
}

@BindingAdapter("textChangedListener")
fun PinView.setTextChangedListener(textWatcher: TextWatcher?) {
    textWatcher?.let {
        addTextChangedListener(it)
    }
}