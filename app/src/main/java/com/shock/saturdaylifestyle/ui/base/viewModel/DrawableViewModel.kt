package com.shock.saturdaylifestyle.ui.base.viewModel

import androidx.annotation.DrawableRes

/**
 * This data class is for accessing context dependent drawable resources inside viewModel
 */

data class DrawableViewModel(
    @DrawableRes val drawable: Int = 0
)