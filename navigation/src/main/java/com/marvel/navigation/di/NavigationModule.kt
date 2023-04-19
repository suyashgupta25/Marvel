package com.marvel.navigation.di

import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator
import com.marvel.navigation.base.Screen
import com.marvel.navigation.base.ScreenProvider
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

/**
 * The [Multibinds] functions indicate that the maps will be provided by other modules, using [IntoMap].
 * Including this will guarantee that an empty map is provided in case there are no bindings available.
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class NavigationModule {

    @Multibinds
    abstract fun multibindRouteNavigators(): Map<Class<out Route>, RouteNavigator<out Route>>

    @Multibinds
    abstract fun multibindScreenProviders(): Map<Class<out Screen>, ScreenProvider<out Screen>>
}
