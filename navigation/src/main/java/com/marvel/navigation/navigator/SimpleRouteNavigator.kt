package com.marvel.navigation.navigator

import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator

/**
 * The most simple kind of [RouteNavigator]. It only needs the [Route] to know how to navigate it.
 */
interface SimpleRouteNavigator<R : Route> : RouteNavigator<R> {
    fun navigate(route: R)
}
