package com.marvel.home.navigation

import androidx.fragment.app.FragmentManager
import com.marvel.home.ui.HomeFragment
import com.marvel.navigation.navigator.FragmentRouteNavigator
import com.marvel.navigation.route.CharactersHomeRoute
import javax.inject.Inject

internal class HomeCharactersRouteNavigator @Inject constructor() :
    FragmentRouteNavigator<CharactersHomeRoute> {

    override fun navigate(route: CharactersHomeRoute, fragmentManager: FragmentManager) {
        val fragment = HomeFragment.newInstance(route.containerId)
        fragmentManager.beginTransaction()
            .add(route.containerId, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
