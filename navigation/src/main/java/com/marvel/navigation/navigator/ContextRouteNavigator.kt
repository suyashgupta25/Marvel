package com.marvel.navigation.navigator

import android.content.Context
import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator

/**
 * This [RouteNavigator] also requires a [Context] to know how to navigate the particular [Route].
 */
interface ContextRouteNavigator<R : Route> : RouteNavigator<R> {
    fun navigate(route: R, context: Context)
}
