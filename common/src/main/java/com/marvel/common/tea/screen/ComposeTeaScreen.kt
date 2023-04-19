package com.marvel.common.tea.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import com.marvel.common.tea.holder.StoreHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

/**
 * The purpose of this class is to provide to a [Composable] screen all the functionalities it needs to work with MVI,
 * so that the States are tailored specific to it and there's no need to interact directly with the [StoreHolder].
 */
class ComposeTeaScreen<Intent : Any, State : Any>(
    private val storeHolder: StoreHolder<Intent, State>
) {

    fun accept(intent: Intent) = storeHolder.store.accept(intent)

    @Composable
    fun states() = storeHolder.store.states
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError { Timber.e("Crash while rendering state", it) }
        .retry()
        .subscribeAsState(storeHolder.store.currentState)
}
