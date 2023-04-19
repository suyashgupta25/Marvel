package com.marvel.navigation.di

import com.marvel.navigation.base.Screen
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ScreenKey(val value: KClass<out Screen>)
