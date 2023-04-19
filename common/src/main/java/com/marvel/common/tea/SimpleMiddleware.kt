package com.marvel.common.tea

import io.reactivex.rxjava3.core.Observable

fun interface SimpleMiddleware<Command : Any, Event : Any> {

    /**
     * Executes a command.
     *
     * Contract for implementations:
     * - Implementations don't have to call subscribeOn
     * - By default subscription will be on the `io` scheduler
     */
    fun execute(command: Command): Observable<Event>
}
