package com.marvel.navigation

import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator
import com.marvel.navigation.base.Screen
import com.marvel.navigation.base.ScreenProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The single responsibility of this hub is to serve as the connection between:
 * - The wanted [Route] and its [RouteNavigator].
 * - The wanted [Screen] and its [ScreenProvider]
 *
 * It requires the destinations to provide an implementation of the [RouteNavigator] or [ScreenProvider] that should be
 * used for navigating the supported [Route] or displaying the supported [Screen].
 */
@Singleton
internal class NavigationHub @Inject constructor() {

    @Inject
    internal lateinit var navigators: Map<Class<out Route>, @JvmSuppressWildcards Provider<RouteNavigator<out Route>>>

    @Inject
    internal lateinit var providers: Map<Class<out Screen>, @JvmSuppressWildcards Provider<ScreenProvider<out Screen>>>

    internal inline fun <R : Route, reified T : RouteNavigator<R>> getNavigatorFor(route: R): RouteNavigator<R>? {
        return when (val navigator = navigators[route::class.java]?.get()) {
            is T -> navigator
            else -> null
        }
    }

    internal inline fun <S : Screen, reified T : ScreenProvider<S>> getProviderFor(screen: S): ScreenProvider<S>? {
        return when (val provider = providers[screen::class.java]?.get()) {
            is T -> provider
            else -> null
        }
    }
}
