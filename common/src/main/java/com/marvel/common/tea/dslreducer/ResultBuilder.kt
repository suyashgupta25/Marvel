package com.marvel.common.tea.dslreducer

/**
 * Allows to collect [Command] using the nice DSL approach
 *
 * <pre>
 *      val builder = ResultBuilder<State, Command>(State())
 *      builder.commands {
 *          +Command()
 *      }
 * </pre>
 */
open class ResultBuilder<State : Any, Command : Any>(
    initialState: State
) {

    private var currentState: State = initialState
    private val commandsBuilder = OperationsBuilder<Command>()

    val state: State
        get() = currentState

    fun state(update: State.() -> State) {
        currentState = currentState.update()
    }

    fun commands(update: OperationsBuilder<Command>.() -> Unit) {
        commandsBuilder.update()
    }

    internal fun build(): Result<State, Command> {
        return Result(currentState, commandsBuilder.build())
    }
}
