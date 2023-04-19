package com.marvel.common.tea

import com.marvel.common.tea.dslreducer.Result

/**
 * Base interface to define a reduce function.
 *
 * Leveraging SAM interfaces to make the code beautiful: https://kotlinlang.org/docs/fun-interfaces.html#sam-conversions
 */
fun interface StateReducer<Event : Any, State : Any, Command : Any> {

    /**
     * Accepts [event] and current [state] -> produces [Result] with new state, and potential side effects
     */
    fun reduce(event: Event, state: State): Result<State, Command>
}
