package com.marvel.navigation.router

import com.marvel.navigation.base.Route


/**
 * This is the base class used for enabling routing. The structure needs at least one [Router] to control which
 * [Route] needs to be taken.
 *
 * Many [Router]s can be implemented to support every kind of [Route] and [RouteNavigator].
 */
interface Router {

    fun routeTo(route: Route)
}
