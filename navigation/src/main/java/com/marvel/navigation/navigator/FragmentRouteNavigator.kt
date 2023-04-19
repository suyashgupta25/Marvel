package com.marvel.navigation.navigator

import androidx.fragment.app.FragmentManager
import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator

/**
 * This very specific [RouteNavigator] also requires a [FragmentManager] to be able to reach the supported [Route].
 */
interface FragmentRouteNavigator<R : Route> : RouteNavigator<R> {
    fun navigate(route: R, fragmentManager: FragmentManager)
}
