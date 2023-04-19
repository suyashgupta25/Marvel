package com.marvel.common.domain

import io.reactivex.rxjava3.core.Single

//Can used by use-case which needs single as return type
interface UseCase<Params, Result : Any> {

    fun get(params: Params): Single<Result>
}
