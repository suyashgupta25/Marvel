package com.marvel.common.domain

import io.reactivex.rxjava3.core.Completable

interface CompletableUseCase<Params> {

    fun execute(params: Params): Completable
}
