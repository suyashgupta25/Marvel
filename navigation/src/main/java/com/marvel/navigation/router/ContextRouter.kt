package com.marvel.navigation.router

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.marvel.navigation.NavigationHub
import com.marvel.navigation.base.Route
import com.marvel.navigation.navigator.ContextRouteNavigator
import com.marvel.navigation.navigator.FragmentRouteNavigator
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

/**
 * This [Router] supports also the [ContextRouteNavigator] and [FragmentRouteNavigator],
 * besides the [SimpleRouteNavigator].
 *
 * @param context references the [AppCompatActivity] this [Router] was injected, it should not be used in the
 * [Application] since the main purpose here is navigation.
 */
internal class ContextRouter @Inject constructor(
    @ActivityContext private val context: Context,
    private val navigationHub: NavigationHub
) : Router {

    @Inject
    internal lateinit var defaultRouter: DefaultRouter

    override fun routeTo(route: Route) {
        val navigator = navigationHub.getNavigatorFor(route)

        when (navigator) {
            is FragmentRouteNavigator -> context.navigateToFragment(route, navigator)
            is ContextRouteNavigator -> navigator.navigate(route, context)
            else -> defaultRouter.routeTo(route)
        }
    }

    private fun <R : Route> Context.navigateToFragment(
        route: R,
        navigator: FragmentRouteNavigator<R>
    ) {
        if (this is AppCompatActivity) {
            navigator.navigate(route, supportFragmentManager)
        } else {
            Timber.e(
                "Not possible to navigate, invalid Context: %s. \nContextRouter requires an AppCompatActivity Context.",
                this::class.simpleName
            )
        }
    }
}
