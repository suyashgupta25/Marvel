package com.marvel.core.usecase

import com.marvel.common.domain.UseCase
import io.reactivex.rxjava3.core.Single
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

/**
 * Use-case for calculating md5 hash based on public and private key
 */
class GetHashForRemoteServiceUseCase @Inject constructor() :
    UseCase<GetHashForRemoteServiceUseCase.Params, String> {

    override fun get(params: Params): Single<String> {
        val input = params.timestamp + params.privateApiKey + params.publicApiKey
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)
            .padStart(32, '0')
        return Single.just(hash)
    }

    data class Params(val timestamp: String, val publicApiKey: String, val privateApiKey: String)
}
