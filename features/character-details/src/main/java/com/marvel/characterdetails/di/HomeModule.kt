package com.marvel.characterdetails.di

import com.marvel.characterdetails.navigation.HomeCharacterDetailsRouteNavigator
import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator
import com.marvel.navigation.di.RouteKey
import com.marvel.navigation.route.CharacterDetailsRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@InstallIn(SingletonComponent::class)
@Module
internal abstract class HomeModule {

    @Binds
    @IntoMap
    @RouteKey(CharacterDetailsRoute::class)
    abstract fun bindHomeCharacterDetailsRoute(homeCharacterDetailsRouteNavigator: HomeCharacterDetailsRouteNavigator): RouteNavigator<out Route>
}
