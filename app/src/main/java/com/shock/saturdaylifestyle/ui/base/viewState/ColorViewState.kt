package com.shock.saturdaylifestyle.ui.base.viewState

import androidx.annotation.ColorRes

/**
 * This data class is for accessing context dependent color resources inside viewModel
 */

data class ColorViewState(
    @ColorRes val color: Int = 0
)