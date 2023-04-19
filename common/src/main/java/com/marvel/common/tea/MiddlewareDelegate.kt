package com.marvel.common.tea

import io.reactivex.rxjava3.core.Observable

/**
 * Receives all [Command]s and can process them internally or can delegate them to Middlewares
 */
interface MiddlewareDelegate<Command : Any, Event : Any> {

    fun processCommands(command: Command): Observable<Event>
}
