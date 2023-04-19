package com.marvel.navigation.factory

import androidx.fragment.app.Fragment
import com.marvel.navigation.NavigationHub
import com.marvel.navigation.base.Screen
import com.marvel.navigation.provider.FragmentScreenProvider
import javax.inject.Inject

/**
 * This [ScreenFactory] supports the [FragmentScreenProvider], it's responsible for fetching the correct [ScreenProvider],
 * given a [Screen], using the [NavigationHub] for that.
 */
internal class FragmentScreenFactory @Inject constructor(
    private val navigationHub: NavigationHub
) : ScreenFactory<Fragment> {

    override fun createFor(screen: Screen): Fragment {
        val provider = navigationHub.getProviderFor(screen)

        return when (provider) {
            is FragmentScreenProvider -> provider.provide(screen)
            null ->
                throw IllegalStateException("Not possible to switch, ScreenProvider is null.")

            else ->
                throw IllegalStateException("Not possible to switch, invalid ScreenProvider: ${provider::class.simpleName}")
        }
    }
}
