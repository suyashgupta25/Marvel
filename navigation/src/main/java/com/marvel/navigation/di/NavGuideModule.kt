package com.marvel.navigation.di

import androidx.fragment.app.Fragment
import com.marvel.navigation.factory.FragmentScreenFactory
import com.marvel.navigation.factory.ScreenFactory
import com.marvel.navigation.router.ContextRouter
import com.marvel.navigation.router.Router
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
internal abstract class NavGuideModule {

    @Binds
    abstract fun bindRouter(contextRouter: ContextRouter): Router

    @Binds
    abstract fun bindFragmentScreenFactory(fragmentScreenFactory: FragmentScreenFactory): ScreenFactory<Fragment>
}
