package com.marvel.navigation.factory

import com.marvel.navigation.base.Screen

/**
 * This is the base class used for enabling screens. The structure needs at least one [ScreenFactory] to control how new
 * [Screen]s are created, using the correct [ScreenProvider].
 *
 * Many [ScreenFactory]s can be implemented to support every kind of [Screen] and [ScreenProvider], depending on the
 * returning type [T].
 */
interface ScreenFactory<T : Any> {
    fun createFor(screen: Screen): T
}
