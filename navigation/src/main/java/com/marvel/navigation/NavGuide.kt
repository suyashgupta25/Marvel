package com.marvel.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marvel.navigation.base.Route
import com.marvel.navigation.base.Screen
import com.marvel.navigation.factory.ScreenFactory
import com.marvel.navigation.router.Router
import dagger.Lazy
import javax.inject.Inject

/**
 * A navigation helper class to be used with Android components, such as [AppCompatActivity] and [Fragment].
 *
 * It allows these components to navigate properly, using the correct [Router] or [ScreenFactory] in a more convenient way.
 */
class NavGuide @Inject constructor(
    private val router: Lazy<Router>,
    private val fragmentScreenFactory: Lazy<ScreenFactory<Fragment>>
) {

    fun navigateTo(route: Route) {
        router.get().routeTo(route)
    }

    fun fragmentFor(screen: Screen): Fragment {
        return fragmentScreenFactory.get().createFor(screen)
    }
}
