package com.marvel.characterdetails.navigation

import androidx.fragment.app.FragmentManager
import com.marvel.characterdetails.ui.CharacterDetailsFragment
import com.marvel.navigation.navigator.FragmentRouteNavigator
import com.marvel.navigation.route.CharacterDetailsRoute
import javax.inject.Inject

internal class HomeCharacterDetailsRouteNavigator @Inject constructor() :
    FragmentRouteNavigator<CharacterDetailsRoute> {

    override fun navigate(route: CharacterDetailsRoute, fragmentManager: FragmentManager) {
        val fragment = CharacterDetailsFragment.newInstance(route.characterId)
        fragmentManager.beginTransaction()
            .add(route.containerId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }
}
