package com.marvel.core.usecase

import org.junit.Test

internal class GetHashForRemoteServiceUseCaseTest {

    private val useCase = GetHashForRemoteServiceUseCase()

    @Test
    fun `Given params to create a hash Then returns a hashed string`() {
        val params = GetHashForRemoteServiceUseCase
            .Params("timestamp", "privateApiKey", "publicApiKey")

        val testObserver = useCase.get(params).test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        assert(testObserver.values().isNotEmpty())
    }
}