package com.marvel.common.tea.store

import io.reactivex.rxjava3.core.Observable

/**
 * Defines main interaction points with MVI
 */
interface Store<Intent, State : Any> {

    /**
     * Access to the latest state
     */
    val currentState: State

    /**
     * Access to stream of states
     */
    val states: Observable<State>

    /**
     * Accepts new Intent to process it
     */
    fun accept(event: Intent)

    /**
     * Verifies if reactive chains are connected
     */
    val isStarted: Boolean

    /**
     * Connects reactive chains
     */
    fun start(): Store<Intent, State>

    /**
     * Releases reactive chains
     */
    fun stop()
}
