package com.shock.saturdaylifestyle.ui.base.viewState

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * This data class is for accessing context dependent drawable resources inside viewModel
 */

data class DrawableViewState(
    @DrawableRes val drawable: Int = 0
)

