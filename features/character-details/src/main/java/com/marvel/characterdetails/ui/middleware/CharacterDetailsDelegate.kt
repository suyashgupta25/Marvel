package com.marvel.characterdetails.ui.middleware

import com.marvel.common.tea.MiddlewareDelegate
import javax.inject.Inject
import com.marvel.characterdetails.ui.CharacterDetailsCommand as Command
import com.marvel.characterdetails.ui.CharacterDetailsEvent as Event

internal class CharacterDetailsDelegate @Inject constructor(
    private val loadInitialDataMiddleware: CharacterDetailsInitialDataMiddleware
) : MiddlewareDelegate<Command, Event.Internal> {

    override fun processCommands(command: Command) = when (command) {
        is Command.LoadInitialData -> loadInitialDataMiddleware.execute(command)
    }
}
