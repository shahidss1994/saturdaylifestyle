package com.shock.saturdaylifestyle.util

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

// TODO: What am I doing here?

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value : KClass<out ViewModel>)