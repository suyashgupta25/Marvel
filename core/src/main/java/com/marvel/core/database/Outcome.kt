package com.marvel.core.database

import com.marvel.core.database.Outcome.Error
import com.marvel.core.database.Outcome.Success

/**
 * Generic class which indicated that operation was finished
 *
 * Could be returned in one of 2 states [Success] or [Error]
 */
sealed class Outcome<out A, out B> {

    /**
     * Indicates that operation was finished successfully
     *
     * Contains [value] of succeed operation
     */
    data class Success<A>(val value: A) : Outcome<A, Nothing>()

    /**
     * Indicates that operation was finished with error
     *
     * Contains [error], to be passed to the caller object
     */
    data class Error<B>(val error: B) : Outcome<Nothing, B>()
}
