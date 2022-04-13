package com.shock.saturdaylifestyle.utility

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

open class MySpannable(isUnderline: Boolean) : ClickableSpan() {


    override fun onClick(view: View) {

    }


    private var isUnderline = true

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isUnderline
        ds.color = Color.parseColor("#5E69E4")
    }


    /**
     * Constructor
     */
    init {
        this.isUnderline = isUnderline
    }
}