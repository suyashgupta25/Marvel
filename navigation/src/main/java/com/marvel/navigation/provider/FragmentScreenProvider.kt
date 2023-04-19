package com.marvel.navigation.provider

import androidx.fragment.app.Fragment
import com.marvel.navigation.base.Screen
import com.marvel.navigation.base.ScreenProvider

/**
 * This specific [ScreenProvider] is used to provide [Screen]s in the form of [Fragment]s.
 */
interface FragmentScreenProvider<S : Screen> : ScreenProvider<S> {
    fun provide(screen: S): Fragment
}
