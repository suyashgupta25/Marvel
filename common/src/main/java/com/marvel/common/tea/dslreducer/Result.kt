package com.marvel.common.tea.dslreducer

/**
 * Represents result of reduce function.
 * Consists of new [State] and list of [Command] to be executed
 */
data class Result<State : Any, Command : Any>(
    val state: State,
    val commands: List<Command>
)