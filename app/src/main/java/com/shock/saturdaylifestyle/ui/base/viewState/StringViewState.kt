package com.shock.saturdaylifestyle.ui.base.viewState

import androidx.annotation.StringRes

/**
 * This data class is for accessing context dependent drawable resources inside viewModel
 */

data class StringViewState(
    @StringRes val stringId: Int = 0
)

