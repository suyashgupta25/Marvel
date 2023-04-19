package com.marvel.navigation.router

import com.marvel.navigation.NavigationHub
import com.marvel.navigation.base.Route
import com.marvel.navigation.navigator.SimpleRouteNavigator
import timber.log.Timber
import javax.inject.Inject

/**
 * The most simple kind of [Router]. It only supports the [SimpleRouteNavigator], without any extra parameters.
 */
internal class DefaultRouter @Inject constructor(
    private val navigationHub: NavigationHub
) : Router {

    override fun routeTo(route: Route) {
        val navigator = navigationHub.getNavigatorFor(route)

        when (navigator) {
            is SimpleRouteNavigator -> navigator.navigate(route)
            null -> Timber.e("Not possible to navigate, the Navigator is null.")
            else -> {
                Timber.e(
                    "Not possible to navigate, unsupported Navigator: %s.",
                    navigator::class.simpleName
                )
            }
        }
    }
}
