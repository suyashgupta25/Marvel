package com.marvel.home.di

import com.marvel.home.navigation.HomeCharactersRouteNavigator
import com.marvel.navigation.base.Route
import com.marvel.navigation.base.RouteNavigator
import com.marvel.navigation.di.RouteKey
import com.marvel.navigation.route.CharactersHomeRoute
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
    @RouteKey(CharactersHomeRoute::class)
    abstract fun bindHomeCharactersRoute(homeCharactersRouteNavigator: HomeCharactersRouteNavigator): RouteNavigator<out Route>

}
