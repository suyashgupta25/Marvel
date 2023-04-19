package com.marvel.testing

import org.mockito.internal.stubbing.defaultanswers.ReturnsDeepStubs
import org.mockito.kotlin.mock

/**
 * Mocking function that stubs every function call inside the mocking object.
 */
inline fun <reified T : Any> mockRelaxed(): T {
    val className = T::class.simpleName
    val stackTrace = Thread.currentThread().stackTrace[1]
    val callingClassName = stackTrace.className.split('.').last()
    val lineNumber = stackTrace.lineNumber

    return mock(
        name = "Mock for $className created at $callingClassName:$lineNumber",
        defaultAnswer = ReturnsDeepStubs(),
        lenient = true
    )
}

inline fun <reified T : Any> mockRelaxedLazy(): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        mockRelaxed()
    }
}
