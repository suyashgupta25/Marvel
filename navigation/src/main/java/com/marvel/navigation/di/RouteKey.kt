package com.marvel.navigation.di

import com.marvel.navigation.base.Route
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class RouteKey(val value: KClass<out Route>)
